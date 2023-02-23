package com.sparta.crud.entity;

import com.sparta.crud.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String content;

    @ManyToOne
    private Memo memo;

    @ManyToOne
    private User user;

    public Comment(User user, Memo memo, String content) {
        this.user = user;
        this.memo = memo;
        this.content = content;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
