package com.backendTask.task2.entity;

import com.backendTask.task2.dto.CustomUserInfoDto;
import com.backendTask.task2.dto.MemberDto;
import com.backendTask.task3.entity.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @OneToMany(mappedBy = "member")  // Order 엔티티에서 "member" 필드와 연관
    private List<Orders> orders;

    public static CustomUserInfoDto customUserToDto(Member member){
        return CustomUserInfoDto.builder()
                .memberId(member.memberId)
                .email(member.email)
                .name(member.name)
                .password(member.password)
                .role(member.role)
                .build();
    }

    public static MemberDto.Response profileToDto(Member member){
        return MemberDto.Response.builder()
                .email(member.email)
                .name(member.name)
                .build();
    }
}
