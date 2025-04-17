package com.example.board.service.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {
    private String userNickname;
    private String userPhoneNumber;
}



