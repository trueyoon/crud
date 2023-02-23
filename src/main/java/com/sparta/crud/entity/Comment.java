package com.sparta.crud.entity;

import com.sparta.crud.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int likeCount;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    List<CommentLike> likes = new ArrayList<>();

    public Comment(User user, Memo memo, String content) {
        this.user = user;
        this.memo = memo;
        this.content = content;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
