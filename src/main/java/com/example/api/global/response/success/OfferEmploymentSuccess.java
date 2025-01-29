package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum OfferEmploymentSuccess implements SuccessCode {
    SEND_OFFEREMPLOYMENT_SUCCESS(HttpStatus.OK, "OFFEREMPLOYMENT001", "제안 요청에 성공했습니다."),
    COMPLETE_OFFEREMPLOYMENT_SUCCESS(HttpStatus.OK, "OFFEREMPLOYMENT002", "알바가 성공적으로 종료되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    OfferEmploymentSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
