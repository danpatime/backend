package com.example.api.review.controller;

import com.example.api.review.ReviewService;
import com.example.api.review.dto.ReviewCommand;
import com.example.api.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
            @AuthenticationPrincipal final Long memberId
    ) {
        final List<ReviewResponse> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByEmployee(
            @AuthenticationPrincipal final Long memberId,
            @PathVariable(required = true) final Long reivewId
    ) {
        final List<ReviewResponse> reviews = reviewService.getReviewsByEmployee(reivewId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/my/reviews")
    public ResponseEntity<List<ReviewResponse>> getMyReviews(
            @AuthenticationPrincipal final Long memberId
            ) {
        final ReviewCommand reviewCommand = new ReviewCommand(memberId);
        final List<ReviewResponse> reviews = reviewService.getReviews(reviewCommand);
        return ResponseEntity.ok(reviews);
    }
}
