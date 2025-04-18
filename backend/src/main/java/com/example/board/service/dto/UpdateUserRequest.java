package com.example.board.service.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor //테스트용
public class UpdateUserRequest {
    private String userNickname;
    private String userPhoneNumber;
}



