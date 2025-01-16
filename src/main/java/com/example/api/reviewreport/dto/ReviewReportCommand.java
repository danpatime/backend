package com.example.api.reviewreport.dto;

import com.example.api.domain.Account;
import com.example.api.domain.Review;
import com.example.api.domain.ReviewReport;

public record ReviewReportCommand(
        Review reviewId,
        Account employeeId,
        String reason
) {
    public ReviewReport toEntity() {
        return new ReviewReport(null, reviewId, employeeId, reason);
    }
}
