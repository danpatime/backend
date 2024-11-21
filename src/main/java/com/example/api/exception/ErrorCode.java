package com.example.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-000", "서버 에러"),
    DUPLICATE_LOGIN_ID(HttpStatus.BAD_REQUEST, "-001", "중복된 ID입니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorDescription;

    ErrorCode(HttpStatus httpStatus, String errorCodeResponse, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCodeResponse;
        this.errorDescription = errorDescription;
    }
}

