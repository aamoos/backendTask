package com.backendTask.task4.entity;

import com.backendTask.task2.entity.Member;
import com.backendTask.task3.dto.OrdersDto;
import com.backendTask.task3.entity.Orders;
import com.backendTask.task4.dto.PostsDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postsId;

    @Column(nullable = false, length = 100) // 제목은 필수로 설정하고 최대 길이를 100자로 제한
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // 내용은 필수로 설정하고 긴 텍스트로 저장
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 작성자는 다른 엔티티(예: Member)로 설정할 수 있습니다.
    @JoinColumn(name = "member_id", nullable = false) // 작성자 외래키
    private Member member;

    @Column(nullable = false) // 작성일은 필수로 설정
    private LocalDateTime createdAt;

    @Column(nullable = false) // 작성일은 필수로 설정
    private LocalDateTime updatedAt;

    public void changePosts(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public static PostsDto.Response toDto(Posts posts){
        return PostsDto.Response.builder()
                .title(posts.getTitle())
                .content(posts.getContent())
                .memberId(posts.getMember().getMemberId())
                .createdAt(posts.getCreatedAt())
                .updatedAt(posts.getUpdatedAt())
                .build();
    }


}
