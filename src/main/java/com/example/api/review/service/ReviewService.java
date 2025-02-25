package com.example.api.review.service;

import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.contracts.ContractRepository;
import com.example.api.domain.Review;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.review.dto.ReviewResponse;
import com.example.api.review.dto.ReviewAvailableCommand;
import com.example.api.review.dto.ReviewAvailableResponse;
import com.example.api.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ContractRepository contractRepository;

    @Transactional(readOnly = true)  // 리뷰 전체 조회
    public List<ReviewResponse> getAllReviews(final String nickname, final PageNumberRequest pageNumberRequest) {
        Pageable pageable = PageRequest.of(pageNumberRequest.page()-1 , 15, Sort.by("createdDate").descending());
        return reviewRepository.findReviews(nickname, pageable).getContent();
    }

    @Transactional(readOnly = true)  // 리뷰 상세 조회
    public ReviewResponse getReviewDetail(
            @Validated final Long reviewId
    ) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND_EXCEPTION));
        return ReviewResponse.from(review);
    }

    @Transactional(readOnly = true)  // 나를 대상으로 쓰인 리뷰 조회
    public List<ReviewResponse> getMyReviews(
            @Validated final EmployeeIdRequest employeeIdRequest,
            final PageNumberRequest pageNumberRequest
    ) {
        Pageable pageable = PageRequest.of(pageNumberRequest.page()-1 , 15, Sort.by("createdDate").descending());
        return reviewRepository.findAllByEmployee_AccountId(employeeIdRequest.employeeId(), pageable).stream()
                .map(ReviewResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)  // 작성 가능한 리뷰 조회
    public List<ReviewAvailableResponse> getAvailableReviewTargets(
            final ReviewAvailableCommand command,
            final PageNumberRequest pageNumberRequest) {
        Pageable pageable = PageRequest.of(pageNumberRequest.page()-1 , 15, Sort.by("createdDate").descending());
        return contractRepository.findAvailableReviewsByBusinessId(command.businessId(), pageable).getContent();
    }
}