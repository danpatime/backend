package com.example.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-000", "서버 에러"),


    ACCOUNT_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "-101", "찾을 수 없는 계정"),

    POSSIBLE_TIME_REGISTER_EXCEPTION(HttpStatus.BAD_REQUEST, "-401", "알바 가능 시간 등록 에러"),
    NO_SUCH_BUSINESS_EXCEPTION(HttpStatus.BAD_REQUEST, "-801", "해당 가게를 찾을 수 없음");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorDescription;

    ErrorCode(HttpStatus httpStatus, String errorCodeResponse, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCodeResponse;
        this.errorDescription = errorDescription;
    }
}
