package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Business;
import com.example.api.domain.BusinessCategory;
import com.example.api.domain.Category;
import com.example.api.domain.repository.BusinessCategoryRepository;
import com.example.api.domain.repository.CategoryRepository;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
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
    private final CategoryRepository categoryRepository;
    private final BusinessCategoryRepository businessCategoryRepository;

    @Override
    public void update(Business business, ModifyBusinessCommand command) {
        if (Objects.isNull(command.categoryIds())) {
            return;
        }
        final List<BusinessCategory> businessCategories = findRelatedCategories(command.categoryIds()).stream()
                .map(category -> new BusinessCategory(business, category))
                .collect(Collectors.toList());
        businessCategoryRepository.saveAll(businessCategories);
    }

    private List<Category> findRelatedCategories(final List<Long> categoryIds) {
        final List<Category> categories = categoryRepository.findAllById(categoryIds);
        validate(categories.size(), categoryIds.size());
        return categories;
    }

    private void validate(final int requestCategorySize, final int foundedSize) {
        if (foundedSize != requestCategorySize) {
            throw new BusinessException("카테고리를 찾을 수 없습니다.", ErrorCode.CATEGORY_EXCEPTION);
        }
    }
}
