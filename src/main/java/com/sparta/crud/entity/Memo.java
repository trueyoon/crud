package com.sparta.crud.entity;


import com.sparta.crud.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*@Column(nullable = false)
    private String username;*/
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
//    @Column(nullable = false)
//    private String password;
    @Column(nullable = false)
    private String username;

    @Builder
    public Memo(MemoRequestDto requestDto, User user){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = user.getUsername();
    }

    public Memo(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        //this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        //this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
    public void setUser(User user) {
        this.user = user;
    }
}
