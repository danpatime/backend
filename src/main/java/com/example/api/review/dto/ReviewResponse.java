package com.example.api.review.dto;

import com.example.api.domain.Review;
import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId, //Id
        Long businessId,
        String businessName,
        Long employeeId,
        String employeeNickname,
        LocalDateTime contractStartTime,
        LocalDateTime contractEndTime,
        int reviewStarPoint,
        String reviewContent
) {
    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getWriter().getBusinessId(),
                review.getWriter().getBusinessName(),
                review.getEmployee().getAccountId(),
                review.getEmployee().getNickname(),
                review.getContract().getContractStartTime(),
                review.getContract().getContractEndTime(),
                review.getReviewStarPoint(),
                review.getReviewContent()
        );
    }
}


