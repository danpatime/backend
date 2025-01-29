package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum S3Success implements SuccessCode {
    UPLOAD_PROFILE_SUCCESS(HttpStatus.OK, "PROFILE001", "프로필 업로드에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    S3Success(final HttpStatus httpStatus, final String code, final String message) {
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
