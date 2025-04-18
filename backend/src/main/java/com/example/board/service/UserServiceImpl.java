package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.error.ErrorCode;
import com.example.board.error.UserException;
import com.example.board.repository.UserRepository;
import com.example.board.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final String ID_PW_REGEX = "^[a-zA-Z0-9]{5,30}$";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(SignUpRequest req) {
        String loginId = req.getUserLoginId();
        String password = req.getUserPassword();

        // 1) 아이디 포맷 검사
        if (loginId == null || !loginId.matches(ID_PW_REGEX)) {
            throw new UserException(ErrorCode.INVALID_USER_ID);
        }

        // 2) 비밀번호 포맷 검사
        if (password == null || !password.matches(ID_PW_REGEX)) {
            throw new UserException(ErrorCode.INVALID_PASSWORD);
        }

        // 3) 중복 검사
        if (userRepository.existsByUserLoginId(loginId)) {
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);
        }

        // 4) 암호화 & 저장
        String encPwd = passwordEncoder.encode(password);

        User user = User.builder()
                .userLoginId(loginId)
                .userPassword(encPwd)
                .userName(req.getUserName())
                .userNickname(req.getUserNickname())
                .userPhoneNumber(req.getUserPhoneNumber())
                .build();
        userRepository.save(user);
    }

    @Override
    public void validateUsername(String userLoginId) {
        if (userRepository.existsByUserLoginId(userLoginId)) {
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);
        }
    }

    @Override
    public LoginResponse login(LoginRequest req) {
        User u = userRepository.findByUserLoginId(req.getUserLoginId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(req.getUserPassword(), u.getUserPassword())) {
            throw new UserException(ErrorCode.PASSWORD_MISMATCH);
        }
        return LoginResponse.from(u);
    }

    @Override
    public UserInfoResponse getMyInfo(Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        return UserInfoResponse.from(u);
    }

    @Override
    @Transactional
    public void updateMyInfo(Long userId, UpdateUserRequest req) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        u.changeNickname(req.getUserNickname());
        u.changePhone(req.getUserPhoneNumber());
    }

    @Override
    @Transactional
    public void deleteMyAccount(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserInfoResponse getUserInfo(Long userId) {
        return getMyInfo(userId);  // 사실 같은 로직
    }

    //테스트용
    public UserRepository getUserRepository() {
        return this.userRepository;
    }

}
