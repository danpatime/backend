package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum AnnouncementSuccessCode implements SuccessCode {
    CREATE_ANNOUNCEMENT_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT001", "공지 생성에 성공했습니다."),
    LOOK_UP_ANNOUNCEMENTS_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT002", "공지 리스트에 성공했습니다."),
    LOOK_UP_SINGLE_ANNOUNCEMENT_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT003", "단일 공지 조회에 성공했습니다."),
    UPDATE_ANNOUNCEMENT_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT004", "공지 업데이트에 성공했습니다."),
    DELETE_ANNOUNCEMENT_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT005", "공지 삭제에 성공했습니다."),
    SEARCH_ANNOUNCEMENT_SUCCESS(HttpStatus.OK, "ANNOUNCEMENT006", "공지 검색에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    AnnouncementSuccessCode(final HttpStatus httpStatus, final String code, final String message) {
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