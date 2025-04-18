package com.example.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor // 테스트용
public class LoginRequest {

    @NotBlank(message = "DTO ERR 로그인 과정에서 id로 빈 값 들어옴")
    private String userLoginId;

    @NotBlank(message = "DTO ERR 로그인 과정에서 pw로 빈 값 들어옴")
    private String userPassword;
}
