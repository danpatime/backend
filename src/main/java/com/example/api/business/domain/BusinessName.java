package com.example.api.business.domain;

import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessName {
    private static final int NAME_MAX_LENGTH = 20;
    private static final int NAME_MIN_LENGTH = 1;
    private final String name;

    public BusinessName(final String name) {
        validateLength(name);
        this.name = name;
    }

    private void validateLength(final String name) {
        if (NAME_MIN_LENGTH > name.length()) {
            throw new BusinessException(NAME_MIN_LENGTH + " 이상이여야 합니다.", ErrorCode.BUSINESS_DOMAIN_EXCEPTION);
        }
        if (NAME_MAX_LENGTH < name.length()) {
            throw new BusinessException(NAME_MAX_LENGTH + " 이히이여야 합니다.", ErrorCode.BUSINESS_DOMAIN_EXCEPTION);
        }
    }
}
