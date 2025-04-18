package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.error.ErrorCode;
import com.example.board.error.UserException;
import com.example.board.repository.MemoryUserRepository;
import com.example.board.repository.UserRepository;
import com.example.board.service.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new MemoryUserRepository();
        PasswordEncoder encoder = new DummyPasswordEncoder();
        userService = new UserServiceImpl(userRepository, encoder);
    }

    @Test
    void 회원가입_정상작동() {
        SignUpRequest req = new SignUpRequest("jjunhub", "jjun1234", "박상준", "상짱", "01012345678");
        userService.signUp(req);

        User u = userRepository.findByUserLoginId("jjunhub").orElse(null);
        assertThat(u).isNotNull();
        assertThat(u.getUserName()).isEqualTo("박상준");
    }

    @Test
    void 아이디중복검사_예외발생() {
        userRepository.save(User.builder()
                .userLoginId("jjunhub")
                .userPassword("1234")
                .userName("중복유저")
                .userNickname("dup")
                .userPhoneNumber("01000000000")
                .build());

        assertThatThrownBy(() -> userService.validateUsername("jjunhub"))
                .isInstanceOf(UserException.class);
    }

    @Test
    void 아이디형식_검사_실패() {
        SignUpRequest req = new SignUpRequest("@@@", "jjun1234", "박상준", "상짱", "01012345678");
        assertThatThrownBy(() -> userService.signUp(req))
                .isInstanceOf(UserException.class)
                .hasMessageContaining(ErrorCode.INVALID_USER_ID.getMessage());
    }

    @Test
    void 로그인_성공() {
        SignUpRequest req = new SignUpRequest("loginUser", "pw12345", "유저", "닉", null);
        userService.signUp(req);

        LoginRequest login = new LoginRequest("loginUser", "pw12345");
        LoginResponse res = userService.login(login);

        assertThat(res.getUserId()).isNotNull();
    }

    @Test
    void 로그인_비밀번호틀림() {
        userService.signUp(new SignUpRequest("loginFail", "pw12345", "유저", "닉", null));

        LoginRequest login = new LoginRequest("loginFail", "wrongpw");
        assertThatThrownBy(() -> userService.login(login))
                .isInstanceOf(UserException.class);
    }

    @Test
    void 내정보조회_성공() {
        SignUpRequest req = new SignUpRequest("me123456", "pw1234", "이름", "별명", null);
        userService.signUp(req);
        Long userId = userRepository.findByUserLoginId("me123456").get().getId();

        UserInfoResponse res = userService.getMyInfo(userId);
        assertThat(res.getUserNickname()).isEqualTo("별명");
    }

    @Test
    void 정보수정_정상작동() {
        SignUpRequest req = new SignUpRequest("modUser", "pw1234", "홍길동", "길동이", "01012341234");
        userService.signUp(req);
        Long id = userRepository.findByUserLoginId("modUser").get().getId();

        UpdateUserRequest update = new UpdateUserRequest( "변경닉", "01000001111");
        userService.updateMyInfo(id, update);

        User u = userRepository.findById(id).get();
        assertThat(u.getUserNickname()).isEqualTo("변경닉");
    }

    @Test
    void 탈퇴후_조회불가() {
        userService.signUp(new SignUpRequest("me1234567", "pw1234", "ㅂㅂ", "굿바이", null));
        Long id = userRepository.findByUserLoginId("me1234567").get().getId();

        userService.deleteMyAccount(id);

        assertThat(userRepository.findById(id)).isEmpty();
    }

    // 테스트용 dummy encoder
    static class DummyPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return rawPassword.toString().equals(encodedPassword);
        }
    }
}
