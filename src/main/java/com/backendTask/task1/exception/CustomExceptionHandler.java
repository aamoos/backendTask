package com.backendTask.task1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.security.SignatureException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    // Handle CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException ex) {
        // Log the custom exception
        log.error("Custom exception occurred: {}", ex.getMessage());
        // Return a custom error response with the exception message and BAD_REQUEST status
        return ErrorResponseEntity.toResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle JWT SignatureException
    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<ErrorResponseEntity> handleJwtSignatureException(SignatureException e) {
        // Log the JWT signature error
        log.error("JWT Signature Mismatch: {}", e.getMessage());
        // Return a custom error response for invalid JWT token with Unauthorized status
        return ErrorResponseEntity.toResponseEntity("Invalid JWT token: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Handle Method Not Allowed (405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseEntity> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        // Log the Method Not Allowed error
        log.error("Method Not Allowed: {}", e.getMessage());
        // Return a custom error response for 405 Method Not Allowed
        return ErrorResponseEntity.toResponseEntity("Method not allowed: " + e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Handle Internal Server Error (500)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseEntity> handleInternalServerError(Exception e) {
        // Log the general exception
        log.error("Internal Server Error: {}", e.getMessage());
        // Return a custom error response for 500 Internal Server Error
        return ErrorResponseEntity.toResponseEntity("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
