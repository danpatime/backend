package com.example.api.business.dto;

import com.example.api.domain.Location;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AddBusinessCommand(
        @NotNull
        Long requestMemberId,
        String businessName,
        String businessRegistrationNumber,
        String businessOpenDate,
        Location location,
        List<Long> subCategoryIds,
        String representationName,
        String phoneNumber,
        String email

) {
}
