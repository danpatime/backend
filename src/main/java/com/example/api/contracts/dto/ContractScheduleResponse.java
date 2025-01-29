package com.example.api.contracts.dto;

import java.time.LocalDateTime;

public record ContractScheduleResponse(
        Long contractId,
        String businessName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
