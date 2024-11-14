package com.example.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-000", "서버 에러"),

    CONTRACT_EXCEPTION(HttpStatus.BAD_REQUEST, "-400", "계약 정보 에러");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorDescription;

    ErrorCode(HttpStatus httpStatus, String errorCodeResponse, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCodeResponse;
        this.errorDescription = errorDescription;
    }
}
