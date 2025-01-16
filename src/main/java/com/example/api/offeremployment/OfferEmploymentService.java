package com.example.api.offeremployment;

import com.example.api.account.repository.AccountRepository;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.OfferEmployment;
import com.example.api.offeremployment.dto.OfferEmploymentCommand;
import com.example.api.offeremployment.dto.OfferEmploymentRequest;
import com.example.api.offeremployment.dto.OfferEmploymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferEmploymentService {
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final AccountRepository accountRepository;
    private final BusinessRepository businessRepository;

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
}