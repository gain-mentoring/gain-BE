package com.gain.mentoring.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.gain.mentoring.global.exception.CustomException;
import com.gain.mentoring.user.domain.User;
import com.gain.mentoring.user.dto.request.SignupRequest;
import com.gain.mentoring.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("회원가입 테스트")
    class SignupTest {

        @Test
        @DisplayName("회원가입 성공")
        void success_signup() {
            // given
            SignupRequest request = new SignupRequest(
                    "홍길동", "gain123", "test1234@google.com", "Test1234!", "2000.01.01", "MALE", "010-1234-5678"
            );

            // when
            Long id = userService.signup(request);

            // then
            User savedUser = userRepository.findById(id).orElseThrow();
            assertThat(savedUser.getEmail()).isEqualTo(request.getEmail());
            assertThat(savedUser.getUserName()).isEqualTo(request.getUserName());
        }

        @Test
        @DisplayName("회원가입 실패 - 이메일 중복")
        void fail_signup_by_duplicated_email() {
            // given
            SignupRequest request1 = new SignupRequest(
                    "홍길동", "gain123", "test1234@google.com", "Test1234!", "2000.01.01", "MALE", "010-1234-5678"
            );
            SignupRequest request2 = new SignupRequest(
                    "이순신", "gain1233", "test1234@google.com", "Test12343!", "2000.01.01", "MALE", "010-1234-5677"
            );

            // when
            userService.signup(request1);

            // then
            assertThatThrownBy(() -> userService.signup(request2))
                    .isInstanceOf(CustomException.class)
                    .hasMessageContaining("이미 존재하는 회원입니다.");
        }

        @Test
        @DisplayName("회원가입 실패 - 아이디 중복")
        void fail_signup_by_duplicated_username() {
            // given
            SignupRequest request1 = new SignupRequest(
                    "홍길동", "gain123", "test1234@google.com", "Test1234!", "2000.01.01", "MALE", "010-1234-5678"
            );
            SignupRequest request2 = new SignupRequest(
                    "이순신", "gain123", "test12@google.com", "Test12343!", "2000.01.01", "MALE", "010-1234-5677"
            );

            // when
            userService.signup(request1);

            // then
            assertThatThrownBy(() -> userService.signup(request2))
                    .isInstanceOf(CustomException.class)
                    .hasMessageContaining("이미 존재하는 아이디입니다.");
        }
    }
}
