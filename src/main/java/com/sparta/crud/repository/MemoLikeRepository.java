package com.sparta.crud.repository;

import com.sparta.crud.entity.Memo;
import com.sparta.crud.entity.MemoLike;
import com.sparta.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoLikeRepository extends JpaRepository<MemoLike, Long> {
    Optional<MemoLike> findByMemoAndUser(Memo memo, User user);
}
