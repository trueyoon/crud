package com.sparta.crud.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.crud.entity.Memo;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"title", "nickName", "createdAt", "contents" })
public class MemoResponseDto {

    /*@Column(nullable = false)
    private String username;*/
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String nickName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;




    public MemoResponseDto(Memo memo) {
        //this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.nickName = memo.getNickName();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }
//    public MemoResponseDto(MemoRequestDto memoRequestDto) {
//        //this.username = memo.getUsername();
//        this.title = memoRequestDto.getTitle();
//        this.nickName = memoRequestDto.getNickName();
//        this.contents = memoRequestDto.getContents();
//        this.createdAt = memoRequestDto.getCreatedAt();
//        this.modifiedAt = memoRequestDto.getModifiedAt();
//    }
}
