package com.sparta.crud.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparta.crud.entity.Memo;
import com.sparta.crud.entity.Comment;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@JsonPropertyOrder({"title", "username", "createdAt", "contents" })
public class MemoResponseDto {

    /*@Column(nullable = false)
    private String username;*/
//    @Column(nullable = false)
    private String contents;
//    @Column(nullable = false)
    private String title;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;

    private List<CommentResponseDto> commentResponseDtos = new ArrayList<>();




    public MemoResponseDto(Memo memo) {
        //this.username = memo.getUsername();
        this.userName = memo.getUser().getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.likeCount = memo.getLikeCount();
        for (Comment comment : memo.getComments()){
            this.commentResponseDtos.add(CommentResponseDto.from(comment));
        }
    }
}
