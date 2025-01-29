package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum SuggestSuccess implements SuccessCode {
    LOOK_UP_SUGGESTS_STATUS_SUCCESS(HttpStatus.OK, "SUGGEST001", "제안 상태들 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    SuggestSuccess(final HttpStatus httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}