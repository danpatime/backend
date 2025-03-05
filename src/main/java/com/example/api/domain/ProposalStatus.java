package com.example.api.domain;

public enum ProposalStatus {
    PENDING,    // 알바 제안 대기 중
    IN_PROGRESS,    // 알바 계약 체결 중
    COMPLETED,  // 알바 체결 완료
    TERMINATED, // 알바 종료
    FAILED, // 거절
}
