package com.backendTask.task1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum RestResponseCode {
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

    // 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    // 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
