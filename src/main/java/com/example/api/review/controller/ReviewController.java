package com.example.api.review.controller;

import com.example.api.review.ReviewService;
import com.example.api.review.dto.ReviewResponse;
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

    @GetMapping("/available") // 작성 가능 알바 리스트 반환
    public ResponseEntity<List<ReviewAvailableResponse>> getAvailableReviewTargets(
            @RequestParam(required = true) final Long contractId
    ) {
        final ReviewAvailableCommand reviewAvailableCommand = new ReviewAvailableCommand(contractId); // final 및 명확한 변수명
        final List<ReviewAvailableResponse> availableEmployees =
                reviewService.getAvailableReviewTargets(reviewAvailableCommand); // final로 결과 선언
        return ResponseEntity.ok(availableEmployees);
    }
}