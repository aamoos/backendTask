package com.backendTask.task1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto<T> {

    private Integer code;
    private HttpStatus status;
    private String message;
    private T data;

    public RestResponseDto(int value, List<String> errorMessages) {
    }
}
