package com.backendTask.task2.entity;

import com.backendTask.task2.dto.CustomUserInfoDto;
import com.backendTask.task2.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

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
