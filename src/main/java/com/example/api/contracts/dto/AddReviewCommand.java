package com.example.api.contracts.dto;

public record AddReviewCommand(
        Long requestMemberId,
        Long contractId,
        String reviewContent,
        Integer reviewScore
) {
}
