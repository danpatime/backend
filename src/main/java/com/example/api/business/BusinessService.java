package com.example.api.business;

import com.example.api.account.repository.AccountRepository;
import com.example.api.business.dto.AddBusinessCommand;
import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.business.update.BusinessUpdateManager;
import com.example.api.domain.*;
import com.example.api.domain.repository.BusinessCategoryRepository;
import com.example.api.domain.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.example.api.domain.repository.SubCategoryRepository;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final BusinessCategoryRepository businessCategoryRepository;
    private final BusinessUpdateManager businessUpdateManager;
    private final SubCategoryRepository subCategoryRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void updateBusiness(@Validated final ModifyBusinessCommand command) {
        final Business targetBusiness = businessRepository.findById(command.businessId())
                .orElseThrow();
        businessUpdateManager.update(targetBusiness, command);
    }

    @Transactional
    public void addBusiness(@Validated final AddBusinessCommand command) {
        Account requestMember = accountRepository.findById(command.requestMemberId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        requestMember.setEmail(command.email());
        requestMember.setPhone(command.phoneNumber());
        final Business business = new Business(
                command.businessName(),
                command.location(),
                command.representationName(),
                requestMember,
                command.businessOpenDate(),
                command.businessRegistrationNumber()
        );
        final List<BusinessCategory> businessCategories = loadCategories(command.subCategoryIds(), business);
        businessRepository.save(business);
        businessCategoryRepository.saveAll(businessCategories);
    }

    private List<BusinessCategory> loadCategories(final List<Long> categoryIds, final Business business) {
        final List<SubCategory> subCategories = subCategoryRepository.findAllById(categoryIds);
        return subCategories.stream()
                .map(subCategory -> new BusinessCategory(business, subCategory))
                .toList();
    }
}
