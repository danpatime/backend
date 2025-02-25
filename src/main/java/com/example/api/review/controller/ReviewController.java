package com.example.api.review.controller;

import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.review.service.ReviewService;
import com.example.api.review.dto.ReviewResponse;
import com.example.api.review.dto.ReviewAvailableCommand;
import com.example.api.review.dto.ReviewAvailableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping // 리뷰 전체 조회
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
            @RequestParam(defaultValue = "1", required = false) final Integer page,
            @RequestParam(required = false) final String nickname
    ) {
        log.info("nickname = {}", nickname);
        final List<ReviewResponse> reviews = reviewService.getAllReviews(nickname, new PageNumberRequest(page));
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}") // 리뷰 상세 조회
    public ResponseEntity<ReviewResponse> getReviewDetail(
            @PathVariable final Long reviewId
    ) {
        ReviewResponse result = reviewService.getReviewDetail(reviewId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/my/employee") // 나를 대상으로 작성된 리뷰 보기
    public ResponseEntity<List<ReviewResponse>> getMyReviews(
            @AuthenticationPrincipal final Long accountId,
            @RequestParam(defaultValue = "1", required = false) final Integer page
    ) {
        final List<ReviewResponse> reviews = reviewService.getMyReviews(new EmployeeIdRequest(accountId), new PageNumberRequest(page));
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/available") // 작성 가능한 리뷰 조회
    public ResponseEntity<List<ReviewAvailableResponse>> getAvailableReviewTargets(
            @RequestParam(required = true) Long businessId,
            @RequestParam(defaultValue = "1", required = false) final Integer page
    ) {
        List<ReviewAvailableResponse> availableEmployees = reviewService.getAvailableReviewTargets(
                new ReviewAvailableCommand(businessId),
                new PageNumberRequest(page));
        return ResponseEntity.ok(availableEmployees);
    }
}
