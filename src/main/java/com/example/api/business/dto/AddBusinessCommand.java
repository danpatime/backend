package com.example.api.business.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AddBusinessCommand(
        @NotNull
        Long requestMemberId,
        String businessName,
        String location,
        List<Long> categoryIds,
        String representationName

) {
}
