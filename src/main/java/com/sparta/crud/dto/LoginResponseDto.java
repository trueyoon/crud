package com.sparta.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    @Column(nullable = false)
    private String msg;
    private int statusCode;
}
