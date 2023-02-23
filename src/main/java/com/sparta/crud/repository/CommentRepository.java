package com.sparta.crud.repository;

import com.sparta.crud.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByMemoId(Long id);

}
