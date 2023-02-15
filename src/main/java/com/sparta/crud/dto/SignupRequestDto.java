package com.sparta.crud.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter

public class SignupRequestDto {
    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
