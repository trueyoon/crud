package com.sparta.crud.entity;


import com.sparta.crud.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
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
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;

    public Memo(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }
}
