package com.gain.mentoring.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 회원 찾기
    Optional<User> findByEmail(String email);

    // 아이디로 회원 찾기(이메일 회원가입)
    Optional<User> findByUserName(String userName);
}
