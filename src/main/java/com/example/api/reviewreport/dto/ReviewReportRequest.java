package com.example.api.reviewreport.dto;

import com.example.api.domain.Review;

public record ReviewReportRequest(
        String reason
) {
    public ReviewReportCommand toCommand(Review reviewId) {
        return new ReviewReportCommand(reviewId, this.reason);
    }
}
