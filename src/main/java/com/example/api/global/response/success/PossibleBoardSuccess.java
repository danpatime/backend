package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum PossibleBoardSuccess implements SuccessCode {
    ADD_POSSIBLE_TIME_SUCCESS(HttpStatus.OK, "POSSIBLE-BOARD001", "근무 가능 시간 추가에 성공했습니다."),
    GET_POSSIBLE_BOARD_SUCCESS(HttpStatus.OK, "POSSIBLE-BOARD002", "이력서 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    PossibleBoardSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
