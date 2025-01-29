package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum ContractReviewSuccess implements SuccessCode {
    LOOK_UP_ALL_REVIEW_SUCCESS(HttpStatus.OK, "CONTRACT_REVIEW001", "내가 쓴 리뷰 조회에 성공했습니다."),
    WRITE_REVIEW_SUCCESS(HttpStatus.OK, "CONTRACT_REVIEW002", "리뷰 작성에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ContractReviewSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
