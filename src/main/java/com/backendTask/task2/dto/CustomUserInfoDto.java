package com.backendTask.task2.dto;

import com.backendTask.task2.entity.RoleType;
import lombok.*;

//내부에서 쓰는 dto
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomUserInfoDto{
    private Long memberId;
    private String email;
    private String name;
    private String password;
    private RoleType role;

}
