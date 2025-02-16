package com.example.api.contracts.dto;

import com.example.api.domain.ProposalStatus;

import java.time.LocalDateTime;

public record SuggestedBusinessResponse(
        Long businessId,
        LocalDateTime suggestStartDateTime,
        LocalDateTime suggestEndDateTime,
        Integer suggestPartTimePayment,
        ProposalStatus status
) {
}