package com.example.board.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    // 아이디가 형식에 맞지 않을 경우 서비스에서 에러코드를 발생시키고 처리하기 위해
    // DTO 생성 시 유효성 검사하는 로직을 다 빼버림

//    @NotBlank(message = "아이디는 필수입니다.")
//    @Size(min = 5, max = 30)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문자와 숫자만 허용됩니다.")
    private String userLoginId;

//    @NotBlank(message = "비밀번호는 필수입니다.")
//    @Size(min = 5, max = 30)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문자와 숫자만 허용됩니다.")
    private String userPassword;

//    @NotBlank(message = "이름은 필수입니다.")
    private String userName;

//    @NotBlank(message = "닉네임은 필수입니다.")
    private String userNickname;

    private String userPhoneNumber;  // null 허용
}
