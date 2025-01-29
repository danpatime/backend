package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum EmployerSuccess implements SuccessCode {
    LOOK_UP_LIKE_EMPLOYEE_SUCCESS(HttpStatus.OK, "EMPLOYER001", "즐겨찾기한 알바생 조회에 성공했습니다."),
    FETCH_BUSINESSES_SUCCESS(HttpStatus.OK, "EMPLOYER002", "나의 사업체 리스트 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    EmployerSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
