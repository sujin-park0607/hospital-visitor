package com.example.hospitalvisitor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DEPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"User name Not Found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"Not Password"),
    HOSPITAL_NOT_FOUND(HttpStatus.NOT_FOUND, "Hospital Not Found")
    ;

    private HttpStatus status;
    private String message;
}
