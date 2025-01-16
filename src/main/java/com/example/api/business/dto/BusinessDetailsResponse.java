package com.example.api.business.dto;

import com.example.api.account.entity.Location;

import java.util.List;

public record BusinessDetailsResponse(
        String businessName,
        Long businessId,
        BusinessOwner owner,
        Location location,
        List<CategoryInfo> categoryInfos) {
}
