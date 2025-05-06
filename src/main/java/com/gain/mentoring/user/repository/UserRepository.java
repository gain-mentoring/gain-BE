package com.gain.mentoring.user.repository;

import com.gain.mentoring.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 회원 찾기
    Optional<User> findByEmail(String email);

    // 아이디로 회원 찾기(이메일 회원가입)
    Optional<User> findByUserName(String userName);

    // 중복 방지를 위한 존재 여부 확인
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
