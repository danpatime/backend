package com.example.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-000", "서버 에러"),
    DUPLICATE_LOGIN_ID(HttpStatus.BAD_REQUEST, "-100", "중복된 ID입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "-101", "이미 가입된 이메일입니다."),
    INCORRECT_CODE(HttpStatus.BAD_REQUEST, "-103", "이메일 인증에 실패하였습니다."),
    EXPIRATION_DATE_END(HttpStatus.BAD_REQUEST, "-104", "코드의 유효 기간이 만료 되었습니다."),

    FAIL_GENERATE_CODE(HttpStatus.INTERNAL_SERVER_ERROR, "-500", "코드 생성에 실패하였습니다."),
    FAIL_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "-501", "이메일 전송에 실패하였습니다."),
    FAIL_SAVE_CODE(HttpStatus.INTERNAL_SERVER_ERROR, "-502", "코드 저장에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorDescription;

    ErrorCode(HttpStatus httpStatus, String errorCodeResponse, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCodeResponse;
        this.errorDescription = errorDescription;
    }
}

