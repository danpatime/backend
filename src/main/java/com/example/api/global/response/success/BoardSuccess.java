package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum BoardSuccess implements SuccessCode{
    LOOK_UP_BOARD_SUCCESS(HttpStatus.OK, "BOARD001", "이력서 조회에 성공했습니다."),
    CHANGE_OPEN_STATUS_SUCCESS(HttpStatus.OK, "BOARD002", "이력서 열람 상태 변경에 성공했습니다."),
    SUBMIT_BOARD_SUCCESS(HttpStatus.OK, "BOARD003", "이력서 저장에 성공했습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    BoardSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
