package com.example.api.review;

import com.example.api.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import com.example.api.domain.Review;
import com.example.api.review.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ReviewResponse> getReviewsByEmployee(
            @Validated final Long reviewId
    ) {
        return reviewRepository.findReviewsByDynamicQuery(reviewId)
                .stream()
                .map(ReviewResponse::from)
                .toList();

        public List<ReviewResponse> getReviewsByEmployee ( @Validated final ReviewCommand reviewCommand){
            final List<Review> reviews = reviewRepository.findReviewsByAccountId(reviewCommand.accountId());
            return reviews.stream()
                    .map(this::mapToReviewResponse)
                    .collect(Collectors.toList());
        }

        @Transactional
        public Page<ReviewResponse> getReviewsByEmployeeWithPagination (
        @Validated final ReviewCommand reviewCommand,
        final Pageable pageable
            ){
            final Page<Review> reviewsPage = reviewRepository.findReviewsByAccountId(
                    reviewCommand.accountId(),
                    pageable);
            return reviewsPage.map(this::mapToReviewResponse);
        }

        @Transactional
        public List<ReviewResponse> getReviewsByEmployeeWithDetails ( @Validated final ReviewCommand reviewCommand){
            final List<Review> reviews = reviewRepository.findReviewsByAccountIdWithDetails(reviewCommand.accountId());
            return reviews.stream()
                    .map(this::mapToReviewResponse)
                    .collect(Collectors.toList());
        }

        private ReviewResponse mapToReviewResponse ( final Review review){
            final String businessName = review.getBusiness().getBusinessName();
            final Long businessId = review.getBusiness().getBusinessId();
            final LocalDateTime contractSrartTime = review.getContract().getContractStartTime();
            final LocalDateTime contractEndTime = review.getContract().getContractEndTime();
            final int reviewStarPoint = review.getReviewStarPoint();
            final String reviewContent = review.getReviewContent();
            return new ReviewResponse(businessName, businessId, contractSrartTime, contractEndTime, reviewStarPoint, reviewContent);
        }
    }
