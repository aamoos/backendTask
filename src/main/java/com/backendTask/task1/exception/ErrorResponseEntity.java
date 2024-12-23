package com.backendTask.task1.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseEntity {
    private String code;
    private int status;
    private String message;

    // Static method to generate a response from RestResponseCode
    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(RestResponseCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getHttpStatus().value())
                        .code(e.name())
                        .message(e.getMessage())
                        .build()
                );
    }

    // Static method to generate a response with a custom message and status
    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(String message, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponseEntity.builder()
                        .status(status.value())
                        .code("CUSTOM_ERROR") // or you can set a custom code here if necessary
                        .message(message)
                        .build()
                );
    }
}
