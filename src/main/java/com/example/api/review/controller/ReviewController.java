<<<<<<< HEAD
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
}
=======
package com.example.api.review.controller;public class ReviewController {
}
>>>>>>> 2f6b5cc (#53 feat(ReviewCommand): DTO 작성)
