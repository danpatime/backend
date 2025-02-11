package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Business;
import com.example.api.domain.BusinessCategory;
import com.example.api.domain.Category;
import com.example.api.domain.SubCategory;
import com.example.api.domain.repository.BusinessCategoryRepository;
import com.example.api.domain.repository.CategoryRepository;
import com.example.api.domain.repository.SubCategoryRepository;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
@RequiredArgsConstructor
public class UpdateBusinessCategoriesHandlerImpl implements BusinessUpdateHandler {
    private final SubCategoryRepository subCategoryRepository;
    private final BusinessCategoryRepository businessCategoryRepository;

    @Override
    public void update(Business business, ModifyBusinessCommand command) {
        if (Objects.isNull(command.categoryIds())) {
            return;
        }
        final List<BusinessCategory> businessCategories = findRelatedCategories(command.categoryIds()).stream()
                .map(subCategory -> new BusinessCategory(business, subCategory))
                .collect(Collectors.toList());
        businessCategoryRepository.deleteAllByBusinessId(business.getBusinessId());
        businessCategoryRepository.saveAll(businessCategories);
    }

    private List<SubCategory> findRelatedCategories(final List<Long> subCategoryIds) {
        final List<SubCategory> subCategories = subCategoryRepository.findAllById(subCategoryIds);
        validate(subCategories.size(), subCategoryIds.size());
        return subCategories;
    }

    private void validate(final int requestCategorySize, final int foundedSize) {
        if (foundedSize != requestCategorySize) {
            throw new BusinessException("카테고리를 찾을 수 없습니다.", ErrorCode.CATEGORY_EXCEPTION);
        }
    }
}
