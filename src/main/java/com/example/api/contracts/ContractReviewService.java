package com.example.api.contracts;

import com.example.api.account.repository.AccountRepository;
import com.example.api.business.BusinessRepository;
import com.example.api.contracts.dto.AddReviewCommand;
import com.example.api.domain.Account;
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
    private final BusinessRepository businessRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void saveReview(@Validated final AddReviewCommand command) {
        final Contract contract = contractRepository.loadContractWithOfferEmployment(command.contractId())
                .orElseThrow();
        validateContractOwner(command.requestMemberId(), contract.getOfferEmployment().getBusiness());
        Business business = businessRepository.findByBusinessId(command.businessId()).orElseThrow(() -> new BusinessException("해당 비즈니스를 찾을 수 없습니다.", ErrorCode.BUSINESS_DOMAIN_EXCEPTION));
        Account employee = accountRepository.findByAccountId(command.employeeId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        final Review review = new Review(business, employee, command.reviewScore(), command.reviewContent(), contract);
        reviewRepository.save(review);
    }

    private void validateContractOwner(final Long requestMemberId, final Business business) {
        if (!business.getEmployer().getAccountId().equals(requestMemberId)) {
            throw new BusinessException("본인의 계약에만 리뷰가 가능합니다", ErrorCode.CONTRACT_EXCEPTION);
        }
    }
}