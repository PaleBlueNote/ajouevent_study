package com.example.board.service;

import com.example.board.service.dto.*;

public interface UserService {
    // 회원가입
    void signUp(SignUpRequest request);

    // 아이디 중복 체크
    void validateUsername(String userLoginId);

    // 로그인 (성공하면 userId 반환)
    LoginResponse login(LoginRequest request);

    // 내 정보 조회
    UserInfoResponse getMyInfo(Long userId);

    // 내 정보 수정
    void updateMyInfo(Long userId, UpdateUserRequest request);

    // 회원 탈퇴
    void deleteMyAccount(Long userId);

    // 다른 사람 프로필 조회
    UserInfoResponse getUserInfo(Long userId);
}
