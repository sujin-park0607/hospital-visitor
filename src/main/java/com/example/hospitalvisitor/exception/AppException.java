package com.example.hospitalvisitor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}
