package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum AccountSuccess implements SuccessCode{
    SEND_EMAIL_SUCCESS(HttpStatus.OK, "ACCOUNT001", "이메일 전송에 성공했습니다."),
    VERIFY_EMAIL_SUCCESS(HttpStatus.OK, "ACCOUNT002", "이메일 전송에 성공했습니다."),
    SIGN_UP_EMPLOYER_SUCCESS(HttpStatus.OK, "ACCOUNT003", "알바생 회원가입에 성공했습니다."),
    VERIFY_BUSINESS_NUMBER_SUCCESS(HttpStatus.OK, "ACCOUNT004", "사업자 번호 인증에 성공했습니다."),
    SIGN_UP_EMPLOYEE_SUCCESS(HttpStatus.OK, "ACCOUNT005", "사업자 회원가입에 성공했습니다."),
    DELETE_ACCOUNT_SUCCESS(HttpStatus.OK, "ACCOUNT006", "사용자 삭제에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    AccountSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
