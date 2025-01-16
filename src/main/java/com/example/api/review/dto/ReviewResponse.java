package com.example.api.review.dto;

import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.Review;

public record ReviewResponse(
        Long reviewId,
        Business writer,
        Account employee,
        int reviewStarPoint,
        String reviewContent

) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getWriter(),
                review.getEmployee(),
                review.getReviewStarPoint(),
                review.getReviewContent()
        );
    }
}

