package com.example.api.global.response.success;

import org.springframework.http.HttpStatus;

public enum ContractSuccess implements SuccessCode{
    // 계약이 아닌 조건이므로 컨트롤러 위치 변경 필요
    LOOK_UP_ALL_SUGGEST_SUCCESS(HttpStatus.OK, "CONTRACT001", "모든 제안들 조회를 성공했습니다."),
    // 요것두
    ALLOW_SUGGEST_CONTACT_SUCCESS(HttpStatus.OK, "CONTRACT002", "제안 접촉 수락 요청을 성공했습니다."),
    GENERATE_CHAT_ROOM_SUCCESS(HttpStatus.OK, "CONTRACT003", "채팅방 생성에 성공했습니다"),
    ACCEPT_CONTRACT_SUCCESS(HttpStatus.OK, "CONTRACT004", "계약 수락 요청을 성공했습니다"),
    LOOK_UP_CONTRACT_INFO_SUCCESS(HttpStatus.OK, "CONTRACT005", "계약 정보 조회에 성공했습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ContractSuccess(final HttpStatus httpStatus, final String code, final String message) {
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
