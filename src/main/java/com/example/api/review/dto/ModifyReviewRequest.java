package com.example.api.review.dto;

public record ModifyReviewRequest(
        Long reviewId,
        Integer reviewScore,
        String reviewContent
) {
}
