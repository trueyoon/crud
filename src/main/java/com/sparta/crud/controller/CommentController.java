package com.sparta.crud.controller;

import com.sparta.crud.dto.CommentRequestDto;
import com.sparta.crud.dto.CommentResponseDto;
import com.sparta.crud.dto.DeleteMemoResponseDto;
import com.sparta.crud.security.UserDetailsImpl;
import com.sparta.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id, commentRequestDto, userDetails);
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, commentRequestDto, userDetails);
    }

    @DeleteMapping("/comment/{id}")
    public DeleteMemoResponseDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails);
    }

    @PostMapping("/comment/like/{id}")
    public CommentResponseDto likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.likeComment(id, userDetails);
    }
}
