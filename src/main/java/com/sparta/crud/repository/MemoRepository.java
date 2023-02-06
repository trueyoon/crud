package com.sparta.crud.repository;

import com.sparta.crud.entity.Memo;
import javax.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> { //db와 연결
    List<Memo> findAllByOrderByModifiedAtDesc();
}