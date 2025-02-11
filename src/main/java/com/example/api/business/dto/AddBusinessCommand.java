package com.example.api.business.dto;

import com.example.api.business.domain.BusinessLocation;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AddBusinessCommand(
        @NotNull
        Long requestMemberId,
        String businessName,
        String businessRegistrationNumber,
        String businessOpenDate,
        BusinessLocation location,
        List<Long> subCategoryIds,
        String representationName

) {
}
