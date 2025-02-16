package com.example.api.suggest.controller.dto;

import com.example.api.domain.ProposalStatus;

public record SuggestStatusDTO (
    ProposalStatus status,
    String name,
    Integer hourlyPayment,
    String businessName,
    String workTime,
    Long chatRoomId
) {
}
