package com.example.api.review.dto;

public record ReviewCommand(
        Long reviewId,
        int reviewStarPoint,
        String reviewContent,
        Long accountId,
        Long businessId,
        Long contractId) {
}

