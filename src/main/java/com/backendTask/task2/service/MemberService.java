package com.backendTask.task2.service;

import com.backendTask.common.exception.CustomException;
import com.backendTask.common.exception.RestResponseCode;
import com.backendTask.common.jwt.JwtUtil;
import com.backendTask.common.dto.RestResponseDto;
import com.backendTask.task2.dto.CustomUserInfoDto;
import com.backendTask.task2.dto.LoginDto;
import com.backendTask.task2.dto.MemberDto;
import com.backendTask.task2.entity.Member;
import com.backendTask.task2.repository.MemberRepository;
import com.backendTask.common.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static com.backendTask.common.exception.RestResponseCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    /**
     * 로그인
     */
    @Transactional(readOnly = true)
    public String login(LoginDto.Request request) {
        String email = request.getEmail();
        String password = request.getPassword();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
        if(!encoder.matches(password, member.getPassword())) {
            throw new CustomException(USER_NOT_FOUND);
        }

        CustomUserInfoDto info = Member.customUserToDto(member);
        return jwtUtil.createAccessToken(info);
    }

    /**
     * 회원가입
     */
    public RestResponseDto<MemberDto.Response> createMember(MemberDto.Request request, BindingResult bindingResult){

        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            // BindingResult에서 발생한 에러 메시지를 추출
            return ValidationUtil.validationCheck(bindingResult);
        }

        //기존에 회원이 있을경우
        Optional<Member> existingMember = memberRepository.findByEmail(request.getEmail());

        // 기존 회원이 있을 경우 예외를 던짐
        if (existingMember.isPresent()) {
            throw new CustomException(RestResponseCode.MEMBER_DUPLICATE_FAILURE);
        }

        //요청이 정상적인 경우
        try{
            //패스워드 encoder
            request.setPassword(encoder.encode(request.getPassword()));
            memberRepository.save(MemberDto.Request.toEntity(request));

            return RestResponseDto.<MemberDto.Response>builder()
                    .code(MEMBER_CREATE_SUCCESS.getHttpStatus().value())
                    .status(MEMBER_CREATE_SUCCESS.getHttpStatus())
                    .message(MEMBER_CREATE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(MEMBER_CREATE_FAILURE);
        }
    }

    /**
     *  회원 프로필 조회
     */
    @Transactional(readOnly = true)
    public RestResponseDto<MemberDto.Response> profile(String token){

        Member profile = memberRepository.findById(jwtUtil.getUserId(token)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 데이터가 정상적으로 있으면 성공 응답
        return RestResponseDto.<MemberDto.Response>builder()
                .code(MEMBER_PROFILE_SUCCESS.getHttpStatus().value())
                .status(MEMBER_PROFILE_SUCCESS.getHttpStatus())
                .message(MEMBER_PROFILE_SUCCESS.getMessage())
                .data(Member.profileToDto(profile))
                .build();
    }
}
