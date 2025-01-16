package com.example.api.review;

import com.example.api.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import com.example.api.domain.Review;
import com.example.api.review.dto.ReviewCommand;

import java.time.LocalDateTime;

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
    public List<ReviewResponse> getReviewsByEmployee(@Validated final Long reviewId) {
        return reviewRepository.findReviewsByDynamicQuery(reviewId)
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }

    @Transactional
    public List<ReviewResponse> getReviews(@Validated final ReviewCommand reviewCommand) {
        final List<Review> reviews = reviewRepository.findReviewsByEmployee_AccountId(reviewCommand.accountId());
        return mapToReviewResponses(reviews);
    }

    @Transactional
    public List<ReviewResponse> getReviewsByEmployeeWithDetails(@Validated final ReviewCommand reviewCommand) {
        final List<Review> reviews = reviewRepository.findReviewsByAccountIdWithDetails(reviewCommand.accountId());
        return mapToReviewResponses(reviews);
    }

    private List<ReviewResponse> mapToReviewResponses(final List<Review> reviews) {
        return reviews.stream()
                .map(this::mapToReviewResponse)
                .toList();
    }

    private ReviewResponse mapToReviewResponse(final Review review) {
        final String businessName = review.getContract().getOfferEmployment().getBusiness().getBusinessName();
        final Long businessId = review.getContract().getOfferEmployment().getBusiness().getBusinessId();
        final LocalDateTime contractStartTime = review.getContract().getContractStartTime();
        final LocalDateTime contractEndTime = review.getContract().getContractEndTime();
        final int reviewStarPoint = review.getReviewStarPoint();
        final String reviewContent = review.getReviewContent();

        return new ReviewResponse(
                review.getReviewId(),
                businessName,
                businessId,
                contractStartTime,
                contractEndTime,
                reviewStarPoint,
                reviewContent
        );
    }
}

