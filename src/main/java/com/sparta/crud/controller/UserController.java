package com.sparta.crud.controller;

import com.sparta.crud.dto.*;
import com.sparta.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new SignupResponseDto("success", HttpStatus.OK.value()));
    }

//    @ResponseBody
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        userService.login(loginRequestDto, response);
//        return "success";
//    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new LoginResponseDto("로그인 완료",HttpStatus.OK.value()));
    }
}
