package com.example.board.service.dto;

import com.example.board.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPhoneNumber;

    public static UserInfoResponse from(User u) {
        return new UserInfoResponse(u.getId(), u.getUserName(), u.getUserNickname(), u.getUserPhoneNumber());
    }
}
