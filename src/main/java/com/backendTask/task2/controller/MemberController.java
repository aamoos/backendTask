package com.backendTask.task2.controller;

import com.backendTask.task1.dto.RestResponseDto;
import com.backendTask.task2.dto.LoginDto;
import com.backendTask.task2.dto.MemberDto;
import com.backendTask.jwt.JwtUtil;
import com.backendTask.task2.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입
     */

    @PostMapping("/register")
    public ResponseEntity<RestResponseDto<MemberDto.Response>> createItem(@Valid @RequestBody MemberDto.Request request, BindingResult bindingResult){

        RestResponseDto<MemberDto.Response> addItem = memberService.createMember(request, bindingResult);
        return new ResponseEntity<>(addItem, addItem.getStatus());
    }

    /**
     *  로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginDto.Request request) {
        String token = memberService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    /**
     * 로그인한 사용자의 프로파일 조회
     */
    @GetMapping("/profile")
    public ResponseEntity<RestResponseDto<MemberDto.Response>> profile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        RestResponseDto<MemberDto.Response> profile = memberService.profile(jwtUtil.extractTokenFromHeader(authorizationHeader));
        return new ResponseEntity<>(profile, profile.getStatus());
    }
}
