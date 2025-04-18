package com.example.board.controller;

import com.example.board.service.UserService;
import com.example.board.service.dto.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static java.util.Collections.emptyMap;

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
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok(emptyMap());
    }

    /**
     * 아이디 중복 + 형식 검사
     * POST /api/users/validate?id=아이디
     */
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateUsername(@RequestParam("id") String userLoginId) {
        userService.validateUsername(userLoginId);
        return ResponseEntity.ok(emptyMap());
    }

    /**
     * 로그인 + 쿠키 확인
     * POST /api/users/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request,
                                               HttpServletResponse response) {

        LoginResponse loginRes = userService.login(request); // 내부에서 userId 반환

        Cookie cookie = new Cookie("userToken", String.valueOf(loginRes.getUserId()));
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(3600); // 1시간
        response.addCookie(cookie);

        System.out.println("로그인 성공: userId 쿠키 발급 완료");

        return ResponseEntity.ok(loginRes);
    }


    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("userToken", null); // 삭제할 값
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        System.out.println("로그아웃 쿠키 제거 완료");
        return ResponseEntity.ok(emptyMap());
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
    public ResponseEntity<Map<String, Object>> updateMyInfo(@CookieValue(value = "userToken") Long userId,
                             @RequestBody UpdateUserRequest request) {
        userService.updateMyInfo(userId, request);
        return ResponseEntity.ok(emptyMap());
    }

    /**
     * 회원 탈퇴 (쿠키 기반)
     * DELETE /api/users
     */
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteUser(@CookieValue(value = "userToken") Long userId) {
        userService.deleteMyAccount(userId);
        return ResponseEntity.ok(emptyMap());
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
