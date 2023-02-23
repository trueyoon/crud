package com.sparta.crud.repository;

import com.sparta.crud.entity.Comment;
import com.sparta.crud.entity.CommentLike;
import com.sparta.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentAndUser(Comment comment, User user);

}
