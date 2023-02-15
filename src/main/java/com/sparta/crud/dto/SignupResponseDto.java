package com.sparta.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponseDto {
    @Column(nullable = false)
    private String msg;
    private int statusCode;


}
