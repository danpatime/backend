package com.example.api.reviewavailable.controller;

import com.example.api.reviewavailable.ReviewAvailableService;
import com.example.api.reviewavailable.dto.ReviewAvailableCommand;
import com.example.api.reviewavailable.dto.ReviewAvailableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewAvailableController {
    private final ReviewAvailableService reviewAvailableService;

    @GetMapping("/available")
    public ResponseEntity<List<ReviewAvailableResponse>> getAvailableReviewTargets(
            @RequestParam(required = true) Long businessId
    ) {
        ReviewAvailableCommand command = new ReviewAvailableCommand(businessId);
        List<ReviewAvailableResponse> availableEmployees = reviewAvailableService.getAvailableReviewTargets(command);
        return ResponseEntity.ok(availableEmployees);
    }
}

