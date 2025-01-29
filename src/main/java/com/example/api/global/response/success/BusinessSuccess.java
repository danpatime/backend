package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum BusinessSuccess implements SuccessCode {
    LOOK_UP_BUSINESS_SUCCESS(HttpStatus.OK, "BUSINESS001", "나의 사업체 조회에 성공했습니다."),
    MODIFY_BUSINESS_SUCCESS(HttpStatus.OK, "BUSINESS002", "사업체 변경에 성공했습니다."),
    ADD_BUSINESS_SUCCESS(HttpStatus.OK, "BUSINESS003", "사업체 추가에 성공했습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    BusinessSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
