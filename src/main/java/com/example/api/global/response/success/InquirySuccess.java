package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum InquirySuccess implements SuccessCode{
    WRITE_INQUIRY_SUCCESS(HttpStatus.OK, "INQUIRY001", "문의 작성에 성공했습니다."),
    LOOK_UP_INQUIRIES_SUCCESS(HttpStatus.OK, "INQUIRY002", "내가 쓴 문의사항들 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    InquirySuccess(final HttpStatus httpStatus, final String code, final String message) {
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
