package com.backendTask.task1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum RestResponseCode {


    //과제 1번 코드//

    // 아이템 목록 조회
    ITEM_LIST_FETCH_SUCCESS(HttpStatus.OK, "아이템 목록 조회 성공"),
    ITEM_LIST_FETCH_FAILURE(HttpStatus.BAD_REQUEST, "아이템 목록 조회 실패"),

    // 새 아이템 생성
    ITEM_CREATE_SUCCESS(HttpStatus.CREATED, "새 아이템이 성공적으로 생성되었습니다."),
    ITEM_CREATE_FAILURE(HttpStatus.BAD_REQUEST, "새 아이템 생성에 실패했습니다."),

    // 특정 아이템 수정
    ITEM_UPDATE_SUCCESS(HttpStatus.OK, "아이템 수정 성공"),
    ITEM_UPDATE_FAILURE(HttpStatus.BAD_REQUEST, "아이템 수정 실패"),

    // 특정 아이템 삭제
    ITEM_DELETE_SUCCESS(HttpStatus.OK, "아이템 삭제 성공"),
    ITEM_DELETE_FAILURE(HttpStatus.BAD_REQUEST, "아이템 삭제 실패"),

    // 기타 에러 메시지
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),
    INVALID_ITEM_DATA(HttpStatus.BAD_REQUEST, "유효하지 않은 아이템 데이터입니다."),

    // 인증 오류
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),

    // 사용자 관련 오류
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보의 사용자를 찾을 수 없습니다."),

    // 중복된 데이터 오류
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다."),

    //과제 2번 코드//
    //회원가입 성공
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "회원가입이 성공하였습니다."),
    MEMBER_CREATE_FAILURE(HttpStatus.BAD_REQUEST, "회원가입이 실패했습니다."),
    MEMBER_DUPLICATE_FAILURE(HttpStatus.CONFLICT, "회원이 이미 존재합니다."),

    MEMBER_PROFILE_SUCCESS(HttpStatus.OK, "회원 프로필이 정상적으로 조회되었습니다."),

    //400
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "정상적인 요청이 아닙니다."),

    // 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    // 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),

    // 401 UNAUTHORIZED: 인증 실패
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 실패. 유효하지 않은 토큰입니다."),

    // JWT 토큰 관련 오류 코드 추가
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    EMPTY_JWT_CLAIMS(HttpStatus.BAD_REQUEST, "JWT claims 문자열이 비어 있습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
