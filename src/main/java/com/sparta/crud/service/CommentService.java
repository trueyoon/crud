package com.sparta.crud.service;

import com.sparta.crud.dto.CommentRequestDto;
import com.sparta.crud.dto.CommentResponseDto;
import com.sparta.crud.dto.DeleteMemoResponseDto;
import com.sparta.crud.entity.*;
import com.sparta.crud.repository.CommentLikeRepository;
import com.sparta.crud.repository.CommentRepository;
import com.sparta.crud.repository.MemoRepository;
import com.sparta.crud.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;


    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );
        Comment comment = new Comment(userDetails.getUser(), memo, commentRequestDto.getContent());
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );
        if (user.getRole() == UserRoleEnum.ADMIN || user.getUsername().equals(comment.getUser().getUsername())){

            comment.update(commentRequestDto);
            commentRepository.save(comment);
            return new CommentResponseDto(comment);
        }else {
            throw new IllegalArgumentException("관리자 또는 작성자만 수정할 수 있습니다.");
        }
    }

    @Transactional
    public DeleteMemoResponseDto deleteComment(Long id, UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        if (user.getRole() == UserRoleEnum.ADMIN || user.getUsername().equals(comment.getUser().getUsername())) {
            commentRepository.deleteById(id);
            DeleteMemoResponseDto deleteMemoResponseDto = new DeleteMemoResponseDto("success", HttpStatus.OK.value());
            //return id;
            return deleteMemoResponseDto;
        } else {
            throw new IllegalArgumentException("작성자 또는 관리자만 삭제할 수 있습니다.");
        }
    }

    @Transactional
    public CommentResponseDto likeComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new NullPointerException("댓글이 존재하지 않습니다.")
        );
        Optional<CommentLike> commentLike = commentLikeRepository.findByCommentAndUser(comment, userDetails.getUser());
        if (commentLike.isPresent()){
            commentLikeRepository.deleteById(commentLike.get().getId());
            comment.setLikeCount(comment.getLikeCount()-1);
            return new CommentResponseDto(comment);
        }
        commentLikeRepository.save(new CommentLike(comment, userDetails.getUser()));
        comment.setLikeCount(comment.getLikeCount()+1);
        return new CommentResponseDto(comment);
    }
}
