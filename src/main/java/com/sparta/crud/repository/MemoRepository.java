package com.sparta.crud.repository;

import com.sparta.crud.entity.Memo;
import javax.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> { //db와 연결
    //List<Memo> findAllByOrderByModifiedAtDesc();
    List<Memo> findAllByOrderByCreatedAtDesc();
    List<Memo> findById(int id);
    Optional<Memo> findByIdAndUserId(Long id, Long userId);
}