package com.sparta.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Getter
@NoArgsConstructor
@Builder
public class StatusResponseDto<T> {
    private int statusCode;
    private T result;

    public StatusResponseDto(int statusCode, T result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public static <T> StatusResponseDto<T> success(T result) {
        return new StatusResponseDto<>(200, result);
    }

    public static <T> StatusResponseDto<T> fail(int statusCode, T result){
        return new StatusResponseDto<>(statusCode, result);
    }
}
