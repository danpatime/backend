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
    NULL_USER(HttpStatus.BAD_REQUEST, "-105", "존재하지 않는 회원입니다."),
    DELETED_USER(HttpStatus.BAD_REQUEST, "-106", "탈퇴한 회원입니다."),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "-107", "틀린 비밀번호입니다."),

    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "-T1", "올바르지 않은 AccessToken입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "-T2", "만료된 AccessToken입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "T3", "만료된 ReFreshToken"),
    NULL_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED,"T4", "존재하지 않은 ReFreshToken 접근"),
    NOT_ACCESS_TOKEN_FOR_REISSUE(HttpStatus.BAD_REQUEST,"T5","재발급하기에는 유효기간이 남은 AccessToken"),
    TOKEN_MISSING_AUTHORITY(HttpStatus.BAD_REQUEST,"T6","권한 정보가 담겨있지 않은 토큰입니다."),


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

