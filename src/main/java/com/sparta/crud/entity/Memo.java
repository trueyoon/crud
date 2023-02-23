package com.sparta.crud.entity;


import com.sparta.crud.dto.MemoRequestDto;
import lombok.*;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int likeCount;

    //fetch = FetchType.Lazy ???
    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createdAt desc")
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "memo", cascade = CascadeType.REMOVE)
    List<MemoLike> likes = new ArrayList<>();



    @Builder
    public Memo(MemoRequestDto requestDto, User user){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
    }

    public Memo(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        //this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        //this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }



    // Getters and setters
    public void setUser(User user) {
        this.user = user;
    }
}
