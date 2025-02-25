package com.example.api.contracts.dto;

public record AddReviewCommand(
        Long requestMemberId,
        Long businessId,
        Long employeeId,
        Long contractId,
        String reviewContent,
        Integer reviewScore
) {
}
