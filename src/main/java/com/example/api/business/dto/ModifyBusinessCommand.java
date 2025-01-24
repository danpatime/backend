package com.example.api.business.dto;

import com.example.api.account.entity.Location;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ModifyBusinessCommand(
        @NotNull
        Long businessId,
        String businessName,
        Location location,
        String representationName,
        List<Long> categoryIds
) {
}
