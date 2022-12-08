package com.example.hospitalvisitor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserJoinResponse {
    private String userName;
    private String email;
}
