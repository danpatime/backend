package com.example.api.contracts;

import com.example.api.contracts.dto.QueryEmployersReviewCommand;
import com.example.api.contracts.dto.ReviewResponse;
import com.example.api.domain.Review;
import java.util.List;

import com.example.api.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> loadReviewsWithEmployerId(final QueryEmployersReviewCommand command) {
        final List<Review> reviews = reviewRepository.loadReviewsByEmployerId(command.employerId());
        return reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getReviewId(),
                        review.getContract().getContractId(),
                        review.getReviewContent(),
                        review.getReviewStarPoint()))
                .toList();
    }
}
