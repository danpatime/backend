package com.example.api.business.dto;

import com.example.api.business.domain.BusinessLocation;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ModifyBusinessCommand(
        @NotNull
        Long businessId,
        String businessName,
        BusinessLocation location,
        String representationName,
        List<Long> categoryIds
) {
}
