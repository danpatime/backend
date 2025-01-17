package com.example.api.offeremployment;

import com.example.api.account.repository.AccountRepository;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.OfferEmployment;
import com.example.api.domain.repository.OfferEmploymentRepository;
import com.example.api.offeremployment.dto.*;
import com.example.api.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferEmploymentService {
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final AccountRepository accountRepository;
    private final BusinessRepository businessRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public OfferEmploymentResponse sendOfferEmployment(final OfferEmploymentRequest offerEmploymentRequest) {
        final OfferEmploymentCommand offerEmploymentCommand = offerEmploymentRequest.toCommand();
        final Account employee = accountRepository.findById(offerEmploymentCommand.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + offerEmploymentCommand.employeeId()));
        final Business business = businessRepository.findById(offerEmploymentCommand.businessId())
                .orElseThrow(() -> new IllegalArgumentException("Business not found with ID: " + offerEmploymentCommand.businessId()));
        final OfferEmployment offerEmployment = OfferEmployment.fromCommand(
                offerEmploymentCommand,
                employee,
                business
        );
        final OfferEmployment savedOfferEmployment = offerEmploymentRepository.save(offerEmployment);
        return OfferEmploymentResponse.fromEntity(savedOfferEmployment);
    }

    @Transactional
    public void completeOfferEmployment(OfferEmploymentCompleteRequest completeRequest) {
        // offerEmployment를 종료로 변경, 종료 시간 업뎃
        offerEmploymentRepository.updateSuggestStatusToFinishedBySuggestId(completeRequest.suggestId());
        // 알바생 평점 조정
        Integer reviewScore = reviewRepository.findReviewStarPointBySuggestId(completeRequest.suggestId());
        // 알바 횟수 count + 1
        accountRepository.updateWorkCountBySuggestId(completeRequest.suggestId(), reviewScore);
    }
}