package com.example.api.contracts;

import com.example.api.contracts.dto.AddReviewCommand;
import com.example.api.domain.Business;
import com.example.api.domain.Contract;
import com.example.api.domain.Review;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ContractReviewService {
    private final ContractRepository contractRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(@Validated final AddReviewCommand command) {
        final Contract contract = contractRepository.loadContractWithOfferEmployment(command.contractId())
                .orElseThrow();
        validateContractOwner(command.requestMemberId(), contract.getOfferEmployment().getBusiness());
        final Review review = new Review(command.reviewScore(), command.reviewContent(), contract);
        reviewRepository.save(review);
    }

    private void validateContractOwner(final Long requestMemberId, final Business business) {
        if (!business.getEmployer().getAccountId().equals(requestMemberId)) {
            throw new BusinessException("본인의 계약에만 리뷰가 가능합니다", ErrorCode.CONTRACT_EXCEPTION);
        }
    }
}