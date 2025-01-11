package com.example.api.review;

import com.example.api.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findReviewsByDynamicQuery(null)
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }


    @Transactional
    public List<ReviewResponse> getReviewsByEmployee(final Long reviewId) {
        return reviewRepository.findReviewsByDynamicQuery(reviewId)
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }
}

