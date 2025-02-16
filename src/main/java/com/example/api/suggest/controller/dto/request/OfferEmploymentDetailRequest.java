package com.example.api.suggest.controller.dto.request;

import com.example.api.domain.ProposalStatus;

import java.time.LocalDateTime;

public record OfferEmploymentDetailRequest(
        String name,
        String businessName,
        ProposalStatus status,
        Integer hourlyPayment,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long chatRoomId
) {
}
