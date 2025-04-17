package com.example.board.controller;

import com.example.board.service.UserService;
import com.example.board.service.dto.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 + 쿠키 저장
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request,
                                       HttpServletResponse response) {
        userService.signUp(request);

        // 임시 쿠키 생성 (이름: userToken, 값: userLoginId)
        Cookie cookie = new Cookie("userToken", request.getUserLoginId());
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간 유효
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    /**
     * 아이디 중복 + 형식 검사
     * POST /api/users/validate?id=아이디
     */
    @PostMapping("/validate")
    public void validateUsername(@RequestParam("id") String userLoginId) {
        userService.validateUsername(userLoginId);
    }

    /**
     * 로그인 + 쿠키 확인
     * POST /api/users/login
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request,
                               @CookieValue(value = "userToken", required = false) String userToken) {

        System.out.println("쿠키로 들어온 userToken: " + userToken);
        return userService.login(request);
    }

    /**
     * 내 프로필 조회 (쿠키 기반)
     * GET /api/users/info
     */
    @GetMapping("/info")
    public UserInfoResponse getMyInfo(@CookieValue(value = "userToken") Long userId) {
        return userService.getMyInfo(userId);
    }

    /**
     * 내 프로필 수정 (쿠키 기반)
     * PUT /api/users/info
     */
    @PutMapping("/info")
    public void updateMyInfo(@CookieValue(value = "userToken") Long userId,
                             @RequestBody UpdateUserRequest request) {
        userService.updateMyInfo(userId, request);
    }

    /**
     * 회원 탈퇴 (쿠키 기반)
     * DELETE /api/users
     */
    @DeleteMapping
    public void deleteUser(@CookieValue(value = "userToken") Long userId) {
        userService.deleteMyAccount(userId);
    }

    /**
     * 타인 프로필 조회
     * GET /api/users/info/{userId}
     */
    @GetMapping("/info/{userId}")
    public UserInfoResponse getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }
}
