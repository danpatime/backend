package com.example.api.reviewreport.controller;

import com.example.api.domain.Account;
import com.example.api.domain.Review;
import com.example.api.reviewreport.ReviewReportService;
import com.example.api.reviewreport.dto.ReviewReportCommand;
import com.example.api.reviewreport.dto.ReviewReportRequest;
import com.example.api.reviewreport.dto.ReviewReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/info/my/reviews")
@RequiredArgsConstructor
public class ReviewReportController {
    private final ReviewReportService reviewReportService;

    @PostMapping("/{accountId}/{reviewId}/report")
    public ResponseEntity<ReviewReportResponse> reportReview(
            @PathVariable final Review reviewId,
            @PathVariable final Account employeeId,
            @RequestBody final ReviewReportRequest reviewReportRequest
    ) {
        final ReviewReportCommand reviewReportCommand = reviewReportRequest.toCommand(reviewId, employeeId);
        final ReviewReportResponse response = reviewReportService.reportReview(reviewReportCommand);
        return ResponseEntity.ok(response);
    }
}

