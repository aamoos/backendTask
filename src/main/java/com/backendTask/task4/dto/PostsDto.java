package com.backendTask.task4.dto;

import com.backendTask.task2.entity.Member;
import com.backendTask.task4.entity.Posts;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class PostsDto {

    @Data
    public static class Request {

        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 100자 이하로 입력하세요.")
        private String title;

        @NotBlank(message = "내용은 필수입니다.")
        private String content;

        private Member member;

        public static Posts toEntity(PostsDto.Request request){
            return Posts.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .member(request.getMember())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public static class Response {

        private Long postsId;
        private String title;
        private String content;
        private Long memberId; // 작성자 ID
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @QueryProjection
        public Response(Long postsId, String title, String content, Long memberId, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.postsId = postsId;
            this.title = title;
            this.content = content;
            this.memberId = memberId;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
