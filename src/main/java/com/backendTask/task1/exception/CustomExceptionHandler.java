package com.backendTask.task1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }

    // JWT 서명 불일치 오류 처리
    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<ErrorResponseEntity> handleJwtSignatureException(SignatureException e) {
        // 로그에 JWT 서명 불일치 오류 메시지 기록
        log.error("JWT Signature Mismatch: {}", e.getMessage());

        // SignatureException 발생 시 401 Unauthorized 반환
        // 여기에 더 구체적인 에러 메시지나 처리를 추가할 수 있습니다.
        return ErrorResponseEntity.toResponseEntity(RestResponseCode.INVALID_JWT_TOKEN);
    }

    // Handling 405 Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseEntity> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        // Log the error
        log.error("Method Not Allowed: {}", e.getMessage());
        // Return a custom error response for 405
        return ErrorResponseEntity.toResponseEntity(RestResponseCode.METHOD_NOT_ALLOWED);
    }

    // Handling 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseEntity> handleInternalServerError(Exception e) {
        // Log the error
        log.error("Internal Server Error: {}", e.getMessage());
        // Return a custom error response for 500
        return ErrorResponseEntity.toResponseEntity(RestResponseCode.INTERNAL_SERVER_ERROR);
    }
}
