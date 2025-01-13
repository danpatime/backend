package com.example.api.contracts.dto;

public record ReviewResponse(
        Long reviewId,
        Long contractId,
        String reviewContent,
        Integer reviewScore
) {
}
