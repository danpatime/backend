package com.example.api.business;

import com.example.api.business.dto.AddBusinessCommand;
import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.business.update.BusinessUpdateManager;
import com.example.api.domain.Business;
import com.example.api.domain.BusinessCategory;
import com.example.api.domain.Category;
import com.example.api.domain.repository.BusinessCategoryRepository;
import com.example.api.domain.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
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
    private final CategoryRepository categoryRepository;

    @Transactional
    public void updateBusiness(@Validated final ModifyBusinessCommand command) {
        final Business targetBusiness = businessRepository.findById(command.businessId())
                .orElseThrow();
        businessUpdateManager.update(targetBusiness, command);
    }

    @Transactional
    public void addBusiness(@Validated final AddBusinessCommand command) {
        final Business business = new Business(command.businessName(), command.location(), command.representationName());
        final List<BusinessCategory> businessCategories = loadCategories(command.categoryIds(), business);
        businessRepository.save(business);
        businessCategoryRepository.saveAll(businessCategories);
    }

    private List<BusinessCategory> loadCategories(final List<Long> categoryIds, final Business business) {
        final List<Category> categories = categoryRepository.findAllById(categoryIds);
        return categories.stream()
                .map(category -> new BusinessCategory(business, category))
                .toList();
    }
}
