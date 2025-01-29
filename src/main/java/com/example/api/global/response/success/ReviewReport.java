package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum ReviewReport implements SuccessCode {
    REPORT_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW_REPORT001", "리뷰 신고를 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ReviewReport(final HttpStatus httpStatus, final String code, final String message) {
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