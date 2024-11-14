package com.example.api.contracts;

import com.example.api.contracts.dto.AddContractReviewCommand;
import com.example.api.domain.Contract;
import com.example.api.domain.Review;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ContractReviewService {
    private final ReviewRepository reviewRepository;
    private final ContractRepository contractRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public void addContractReview(@Validated final AddContractReviewCommand addContractReviewCommand) {
        final Contract contract = contractRepository.findById(addContractReviewCommand.contractId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CONTRACT_EXCEPTION));
        if (!contract.isContractSucceeded()) {
            throw new BusinessException("계약이 완료되지 않았습니다.", ErrorCode.CONTRACT_EXCEPTION);
        }
        final Review contractReview = reviewMapper.createReview(addContractReviewCommand);
        contract.addReview(contractReview);
        reviewRepository.save(contractReview);
    }
}
