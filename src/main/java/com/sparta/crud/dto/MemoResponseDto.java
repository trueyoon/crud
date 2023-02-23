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
@JsonPropertyOrder({"title", "username", "createdAt", "contents" })
public class MemoResponseDto {

    /*@Column(nullable = false)
    private String username;*/
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;




    public MemoResponseDto(Memo memo) {
        //this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.likeCount = memo.getLikeCount();
    }
}
