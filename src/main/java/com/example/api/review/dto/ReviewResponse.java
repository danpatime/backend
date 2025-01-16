package com.example.api.review.dto;

import com.example.api.domain.Review;
import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId, //Id
        String businessName,
        Long businessId,
        LocalDateTime contractStartTime,
        LocalDateTime contractEndTime,
        int reviewStarPoint,
        String reviewContent
) {
    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getContract().getOfferEmployment().getBusiness().getBusinessName(),
                review.getContract().getOfferEmployment().getBusiness().getBusinessId(),
                review.getContract().getContractStartTime(),
                review.getContract().getContractEndTime(),
                review.getReviewStarPoint(),
                review.getReviewContent()
        );
    }
}


