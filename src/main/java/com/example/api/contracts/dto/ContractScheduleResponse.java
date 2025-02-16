package com.example.api.contracts.dto;

import com.example.api.domain.ProposalStatus;

import java.time.LocalDateTime;

public record ContractScheduleResponse(
        Long contractId,
        String businessName,
        ProposalStatus status,
        Long hourlyPayment,
        LocalDateTime startTime,
        LocalDateTime endTime

) {
}
