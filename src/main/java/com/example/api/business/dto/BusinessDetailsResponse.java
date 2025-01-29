package com.example.api.business.dto;

import com.example.api.business.domain.BusinessLocation;

import java.util.List;

public record BusinessDetailsResponse(
        String businessName,
        Long businessId,
        BusinessOwner owner,
        BusinessLocation location,
        List<CategoryInfo> categoryInfos) {
}
