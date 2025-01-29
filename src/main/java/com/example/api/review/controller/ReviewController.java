package com.example.api.review.controller;

import com.example.api.review.service.ReviewService;
import com.example.api.review.dto.ReviewCommand;
import com.example.api.review.dto.ReviewResponse;
import com.example.api.review.dto.ReviewAvailableCommand;
import com.example.api.review.dto.ReviewAvailableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping // 리뷰 전체 조회
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        final List<ReviewResponse> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}") // 리뷰 상세 조회
    public ResponseEntity<List<ReviewResponse>> getReviewsByEmployee(
            @PathVariable(required = true) Long reivewId
    ) {
        final List<ReviewResponse> reviews = reviewService.getReviewsByEmployee(reivewId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/my/reviews")
    public ResponseEntity<List<ReviewResponse>> getMyReviews(
            @RequestParam final Long accountId
            ) {
        final ReviewCommand reviewCommand = new ReviewCommand(accountId);
        final List<ReviewResponse> reviews = reviewService.getReviews(reviewCommand);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ReviewAvailableResponse>> getAvailableReviewTargets(
            @RequestParam(required = true) Long businessId
    ) {
        ReviewAvailableCommand command = new ReviewAvailableCommand(businessId);
        List<ReviewAvailableResponse> availableEmployees = reviewService.getAvailableReviewTargets(command);
        return ResponseEntity.ok(availableEmployees);
    }
}
