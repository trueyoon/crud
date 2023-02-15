package com.sparta.crud.repository;

import com.sparta.crud.entity.Memo;
import com.sparta.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findById(int id);
    //회원 중복 확인?
}
