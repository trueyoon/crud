package com.sparta.crud.dto;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.util.PrimitiveIterator;

@Getter
@Setter
public class LoginRequestDto {

    private String username;
    private String password;
}
