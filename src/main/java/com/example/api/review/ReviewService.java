package com.example.api.review;

import com.example.api.contracts.ContractRepository;
import com.example.api.domain.Account;
import com.example.api.review.dto.ReviewAvailableResponse;
import com.example.api.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ContractRepository contractRepository;

    @Transactional // 전체 리뷰 조회
    public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }

    @Transactional // 상세 리뷰 조회
    public List<ReviewResponse> getReviewsByEmployee(final Long reviewId) {
        return reviewRepository.findByReviewId(reviewId)
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }

    @Transactional
    public List<ReviewAvailableResponse> getAvailableReviewTargets(Long contractId) {
        return contractRepository.findByContractId(contractId)
                .stream()
                .map(contract -> new ReviewAvailableResponse(
                        contract.getContractId(),            // 계약 ID
                        contract.getEmployee().getAccountId(), // 알바생 ID
                        contract.getEmployee().getName()      // 알바생 이름
                ))
                .toList();
    }
}

