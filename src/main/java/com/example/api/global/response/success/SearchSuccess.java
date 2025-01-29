package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum SearchSuccess implements SuccessCode {
    SEARCH_EMPLOYEE_SUCCESS(HttpStatus.OK, "SEARCH001", "조건에 맞는 알바생 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    SearchSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
