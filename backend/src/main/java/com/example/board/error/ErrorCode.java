package com.example.board.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_USER_ID(409001, "유효하지 않은 아이디 형식입니다."),
    DUPLICATE_USER_ID(409002, "이미 존재하는 아이디입니다."),
    USER_NOT_FOUND(404002, "해당하는 유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(409003, "유효하지 않은 비밀번호 형식입니다. 영문자와 숫자만 사용해주세요."),
    PASSWORD_MISMATCH(409004, "비밀번호가 틀렸습니다.");

    private final int code;
    private final String message;
}
