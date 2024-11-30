package com.example.api.business;

import com.example.api.business.dto.BusinessDetailsResponse;
import com.example.api.business.dto.BusinessOwner;
import com.example.api.business.dto.CategoryInfo;
import com.example.api.business.dto.QueryBusinessDetailCommand;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import java.util.List;
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
    private final BusinessCategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public BusinessDetailsResponse loadDetails(@Validated final QueryBusinessDetailCommand queryBusinessDetailCommand) {
        final Business business = businessRepository.findById(queryBusinessDetailCommand.businessId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NO_SUCH_BUSINESS_EXCEPTION));
        final Account account = business.getEmployer();

        final List<CategoryInfo> categoryInfos = categoryRepository.queryBusinessCategoriesById(queryBusinessDetailCommand.businessId());
        final BusinessOwner owner = new BusinessOwner(account.getAccountId(), account.getName());
        return new BusinessDetailsResponse(business.getBusinessName(), business.getBusinessId(), owner, business.getLocation(), categoryInfos);
    }
}
