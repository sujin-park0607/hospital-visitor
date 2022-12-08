package com.example.hospitalvisitor.domain.dto;

import com.example.hospitalvisitor.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password)
                .email(this.email)
                .build();
    }
}
