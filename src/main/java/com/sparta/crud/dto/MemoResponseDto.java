package com.sparta.crud.dto;


import com.sparta.crud.entity.Memo;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class MemoResponseDto {

    /*@Column(nullable = false)
    private String username;*/
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String nickName;

    public MemoResponseDto(Memo memo) {
        //this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.nickName = memo.getNickName();
        this.title = memo.getTitle();
    }
}
