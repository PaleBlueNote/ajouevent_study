package com.example.board.service.dto;

import com.example.board.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponse {
    private Long userId;

    public static LoginResponse from(User user){
        return new LoginResponse(user.getId());
    }
}
