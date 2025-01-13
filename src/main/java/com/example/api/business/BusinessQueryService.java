package com.example.api.business;

import com.example.api.business.dto.BusinessDetailsResponse;
import com.example.api.business.dto.BusinessOwner;
import com.example.api.business.dto.CategoryInfo;
import com.example.api.business.dto.QueryBusinessDetailCommand;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.BusinessCategory;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BusinessQueryService {
    private final BusinessRepository businessRepository;

    public BusinessDetailsResponse loadDetails(@Validated final QueryBusinessDetailCommand queryBusinessDetailCommand) {
        final Business business = businessRepository.getDetails(queryBusinessDetailCommand.businessId())
                .orElseThrow(() -> new BusinessException("해당 비즈니스를 찾을 수 없습니다.", ErrorCode.BUSINESS_DOMAIN_EXCEPTION));
        final Account account = business.getEmployer();
        final List<CategoryInfo> categoryInfos = business.getBusinessCategories().stream()
                .map(BusinessCategory::getCategory)
                .map(category -> new CategoryInfo(category.getCategoryId(), category.getCategoryName()))
                .toList();
        final BusinessOwner owner = new BusinessOwner(account.getAccountId(), account.getName());
        return new BusinessDetailsResponse(business.getBusinessName(), business.getBusinessId(), owner, business.getLocation(), categoryInfos);
    }
}
