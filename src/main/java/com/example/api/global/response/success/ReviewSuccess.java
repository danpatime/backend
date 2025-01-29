package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum ReviewSuccess implements SuccessCode{
    LOOK_UP_ALL_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW001", "모든 리뷰 조회에 성공했습니다."),
    LOOK_UP_DETAIL_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW002", "리뷰 상세 조회에 성공했습니다."),
    LOOK_UP_MY_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW003", "내가 쓴 리뷰 조회에 성공했습니다."),
    LOOK_UP_AVAILABLE_SUCCESS(HttpStatus.OK, "REVIEW004", "리뷰를 적을 수 있는 직원 리스트 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;

    private final String message;

    ReviewSuccess(final HttpStatus httpStatus, final String code, final String message) {
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