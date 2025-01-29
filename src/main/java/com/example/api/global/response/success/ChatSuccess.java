package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum ChatSuccess implements SuccessCode{
    SEND_CHAT_SUCCESS(HttpStatus.OK, "CHAT001", "채팅 전송에 성공했습니다."),
    READ_CHAT_SUCCESS(HttpStatus.OK, "CHAT002", "채팅 읽기에 성공했습니다."),
    SUMMARIZE_CHAT_SUCCESS(HttpStatus.OK, "CHAT003", "채팅 요약에 성공했습니다"),
    GET_CHAT_SUCCESS(HttpStatus.OK, "CHAT004", "채팅 가져오기에 성공했습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ChatSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
