package com.sparta.crud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
//    @Size(min = 4, max = 10)
//    @Pattern(regexp = "^[a-z0-9]*$")
    private String username;

    @Column(nullable = false)
//    @Size(min = 8, max = 15)
//    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }
}
