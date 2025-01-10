package com.backendTask.util;

import com.backendTask.task1.dto.RestResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

import static com.backendTask.exception.RestResponseCode.BAD_REQUEST;

public class ValidationUtil {

    // 제네릭 메서드로 에러 메시지를 추출하여 공통 응답을 반환
    public static <T> RestResponseDto<T> validationCheck(BindingResult bindingResult) {
        // BindingResult에서 발생한 에러 메시지를 추출
        String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", ")); // 에러 메시지들을 쉼표로 구분

        return RestResponseDto.<T>builder()
                .code(BAD_REQUEST.getHttpStatus().value())
                .status(BAD_REQUEST.getHttpStatus())
                .message(errorMessage)
                .build();
    }

}
