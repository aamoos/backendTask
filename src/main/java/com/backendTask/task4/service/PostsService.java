package com.backendTask.task4.service;

import com.backendTask.exception.CustomException;
import com.backendTask.jwt.JwtUtil;
import com.backendTask.task1.dto.ItemsDto;
import com.backendTask.task1.dto.RestResponseDto;
import com.backendTask.task2.repository.MemberRepository;
import com.backendTask.task4.dto.PostsDto;
import com.backendTask.task4.dto.QPostsDto_Response;
import com.backendTask.task4.entity.Posts;
import com.backendTask.task4.repository.PostsRepository;
import com.backendTask.util.ValidationUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import static com.backendTask.exception.RestResponseCode.*;
import static com.backendTask.task4.entity.QPosts.posts;

@Service
@RequiredArgsConstructor
@Transactional
public class PostsService {

    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;
    private final JwtUtil jwtUtil;
    private final JPAQueryFactory queryFactory;


    /**
     * 아이템 조회
     */
    @Transactional(readOnly = true)
    public RestResponseDto<Page<PostsDto.Response>> getPosts(Pageable pageable) {

        // QueryDSL을 사용하여 필요한 데이터를 PostsDto.Response로 프로젝션
        List<PostsDto.Response> fetch = queryFactory
                .select(new QPostsDto_Response(
                        posts.postsId,
                        posts.title,
                        posts.content,
                        posts.member.memberId,  // memberId로 프로젝션
                        posts.createdAt,
                        posts.updatedAt
                ))
                .from(posts)
                .orderBy(posts.createdAt.desc())  // 최신 게시글부터 조회
                .offset(pageable.getOffset())  // 페이징 처리 (offset)
                .limit(pageable.getPageSize())  // 페이징 처리 (limit)
                .fetch();

        // 총 게시글 수를 구함
        long total = queryFactory
                .selectFrom(posts)
                .fetchCount();

        // PageImpl을 사용하여 페이징 처리된 결과 반환
        Page<PostsDto.Response> responsePage = new PageImpl<>(fetch, pageable, total);

        // 데이터가 정상적으로 있으면 성공 응답
        return RestResponseDto.<Page<PostsDto.Response>>builder()
                .code(POSTS_LIST_FETCH_SUCCESS.getHttpStatus().value())
                .status(POSTS_LIST_FETCH_SUCCESS.getHttpStatus())
                .message(POSTS_LIST_FETCH_SUCCESS.getMessage())
                .data(responsePage)  // 페이징 처리된 데이터
                .build();
    }

    /**
     * 게시판 글쓰기
     */
    public RestResponseDto<PostsDto.Response> createPosts(String authorizationHeader
            , PostsDto.Request request
            , BindingResult bindingResult){

        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validationCheck(bindingResult);
        }

        //요청이 정상적인 경우
        try{
            //로그인한 사용자 정보 set
            request.setMember(memberRepository.findById(jwtUtil.getUserId(jwtUtil.extractTokenFromHeader(authorizationHeader))).orElseThrow(() -> new CustomException(USER_NOT_FOUND)));

            Posts posts = PostsDto.Request.toEntity(request);

            //주문하기 저장
            postsRepository.save(posts);

            return RestResponseDto.<PostsDto.Response>builder()
                    .code(POSTS_CREATE_SUCCESS.getHttpStatus().value())
                    .status(POSTS_CREATE_SUCCESS.getHttpStatus())
                    .message(POSTS_CREATE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(POSTS_CREATE_FAILURE);
        }
    }

    /**
     * 게시물 상세 조회
     */
    @Transactional(readOnly = true)
    public RestResponseDto<PostsDto.Response> readPosts(Long id){

        Optional<Posts> findPosts = postsRepository.findById(id);

        Posts posts = findPosts.orElseThrow(() -> new CustomException(POSTS_READ_FAILURE));

        // 데이터가 정상적으로 있으면 성공 응답
        return RestResponseDto.<PostsDto.Response>builder()
                .code(POSTS_READ_SUCCESS.getHttpStatus().value())
                .status(POSTS_READ_SUCCESS.getHttpStatus())
                .message(POSTS_READ_SUCCESS.getMessage())
                .data(Posts.toDto(posts))
                .build();
    }

    /**
     * 게시물 수정
     */
    public RestResponseDto<PostsDto.Response> updatePosts(Long id, PostsDto.Request request, BindingResult bindingResult){

        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validationCheck(bindingResult);
        }

        try{
            Optional<Posts> findPosts = postsRepository.findById(id);
            Posts posts = findPosts.orElseThrow(() -> new CustomException(POSTS_NOT_FOUND));

            //변경감지 데이터 update
            posts.changePosts(request.getTitle(), request.getContent());

            return RestResponseDto.<PostsDto.Response>builder()
                    .code(POSTS_UPDATE_SUCCESS.getHttpStatus().value())
                    .status(POSTS_UPDATE_SUCCESS.getHttpStatus())
                    .message(POSTS_UPDATE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(POSTS_UPDATE_FAILURE);
        }
    }

    /**
     * 게시물 삭제
     */
    public RestResponseDto<PostsDto.Response> deletePosts(Long id){

        try{
            //삭제할 아이템 존재하는지 확인
            Optional<Posts> findPosts = postsRepository.findById(id);
            findPosts.orElseThrow(() -> new CustomException(POSTS_NOT_FOUND));

            postsRepository.deleteById(id);

            return RestResponseDto.<PostsDto.Response>builder()
                    .code(POSTS_DELETE_SUCCESS.getHttpStatus().value())
                    .status(POSTS_DELETE_SUCCESS.getHttpStatus())
                    .message(POSTS_DELETE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(POSTS_DELETE_FAILURE);
        }
    }

}
