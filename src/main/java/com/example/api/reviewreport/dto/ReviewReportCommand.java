package com.example.api.reviewreport.dto;

import com.example.api.domain.Review;
import com.example.api.domain.ReviewReport;

public record ReviewReportCommand(
        Review review,
        String reason
) {
    public ReviewReport toEntity() {
        return new ReviewReport(review, reason);
    }
}

