package com.gain.mentoring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER("이미 존재하는 회원입니다.", HttpStatus.CONFLICT);
    // 추후 추가

    private final String message;
    private final HttpStatus status;
}
