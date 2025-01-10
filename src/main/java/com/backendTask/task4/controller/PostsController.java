package com.backendTask.task4.controller;

import com.backendTask.task1.dto.ItemsDto;
import com.backendTask.task1.dto.RestResponseDto;
import com.backendTask.task4.dto.PostsDto;
import com.backendTask.task4.service.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    /**
     * 게시판 페이징 목록 조회
     */
    @GetMapping("/posts")
    public ResponseEntity<RestResponseDto<Page<PostsDto.Response>>> getPosts(Pageable pageable){
        RestResponseDto<Page<PostsDto.Response>> getPosts = postsService.getPosts(pageable);
        return new ResponseEntity<>(getPosts, getPosts.getStatus());
    }

    /**
     * 게시물 등록하기
     */
    @PostMapping("/posts")
    public ResponseEntity<RestResponseDto<PostsDto.Response>> createPosts(
              @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @Valid @RequestBody PostsDto.Request request
            , BindingResult bindingResult){
        RestResponseDto<PostsDto.Response> posts = postsService.createPosts(authorizationHeader, request, bindingResult);
        return new ResponseEntity<>(posts, posts.getStatus());
    }

    /**
     * 게시물 상세조회
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<RestResponseDto<PostsDto.Response>> readOrders(@PathVariable("id") Long id){
        RestResponseDto<PostsDto.Response> readPosts = postsService.readPosts(id);
        return new ResponseEntity<>(readPosts, readPosts.getStatus());
    }

    /**
     * 게시물 수정
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<RestResponseDto<PostsDto.Response>> updatePosts(
              @PathVariable("id") Long id
            , @Valid @RequestBody PostsDto.Request request
            , BindingResult bindingResult){
        RestResponseDto<PostsDto.Response> updatePosts = postsService.updatePosts(id, request, bindingResult);
        return new ResponseEntity<>(updatePosts, updatePosts.getStatus());
    }

    /**
     * 게시물 삭제
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<RestResponseDto<PostsDto.Response>> deleteOrders(@PathVariable("id") Long id){
        RestResponseDto<PostsDto.Response> deletePosts = postsService.deletePosts(id);
        return new ResponseEntity<>(deletePosts, deletePosts.getStatus());
    }

}
