package com.sparta.crud.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoRequestDto {
    private String username;
    private String title;
    private String contents;
    //cprivate String password;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

}
