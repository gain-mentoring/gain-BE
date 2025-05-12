package com.gain.mentoring.user.service;

import com.gain.mentoring.global.exception.CustomException;
import com.gain.mentoring.global.exception.ErrorCode;
import com.gain.mentoring.global.jwt.JwtToken;
import com.gain.mentoring.global.jwt.JwtTokenProvider;
import com.gain.mentoring.user.domain.Gender;
import com.gain.mentoring.user.domain.OauthProvider;
import com.gain.mentoring.user.domain.Role;
import com.gain.mentoring.user.domain.User;
import com.gain.mentoring.user.dto.request.LoginRequest;
import com.gain.mentoring.user.dto.request.SignupRequest;
import com.gain.mentoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATED_USER);
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new CustomException(ErrorCode.DUPLICATED_USERID);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Role role = Role.MENTEE; // default 설정
        Gender gender = Gender.valueOf(request.getGender().toUpperCase());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .oauthProvider(OauthProvider.NONE)
                .userName(request.getUserName())
                .password(encodedPassword)
                .role(role)
                .birthday(request.getBirthday())
                .gender(gender)
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

        return user.getId();
    }

    public JwtToken login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_REGISTERED_USER));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.NOT_MATCHED_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        return jwtTokenProvider.generateToken(authentication);
    }
}
