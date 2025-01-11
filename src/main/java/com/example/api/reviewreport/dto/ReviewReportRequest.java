package com.example.api.reviewreport.dto;

import com.example.api.domain.Account;
import com.example.api.domain.Review;

public record ReviewReportRequest(
        String reason
) {
    public ReviewReportCommand toCommand(Review reviewId, Account employeeId) {
        return new ReviewReportCommand(reviewId, employeeId, this.reason);
    }
}
