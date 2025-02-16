package com.example.api.domain;

public enum ProposalStatus {
    PENDING,    // 대기 중
    IN_PROGRESS,    // 체결 중
    COMPLETED,  // 체결 완료
    TERMINATED, // 종료
    FAILED, // 거절
}
