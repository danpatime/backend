package com.example.api.business.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ModifyBusinessCommand(
        @NotNull
        Long businessId,
        String businessName,
        String location,
        String representationName,
        List<Long> categoryIds
) {
}
