package com.sparta.crud.dto;

import com.sparta.crud.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponseDto {
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUser().getUsername();
        this.likeCount = comment.getLikeCount();
    }

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .content(comment.getContent())
                .username(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .likeCount(comment.getLikeCount())
                .build();
    }

    // static CommentResponseDto from (Comment comment ? )
    // builder ???

}
