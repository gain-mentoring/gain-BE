package com.gain.mentoring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER("이미 존재하는 회원입니다.", HttpStatus.CONFLICT),
    DUPLICATED_USERID("이미 존재하는 아이디입니다.", HttpStatus.CONFLICT),
    NOT_REGISTERED_USER("존재하지 않은 이메일입니다.", HttpStatus.UNAUTHORIZED),
    NOT_MATCHED_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
    // 추후 추가

    private final String message;
    private final HttpStatus status;
}
