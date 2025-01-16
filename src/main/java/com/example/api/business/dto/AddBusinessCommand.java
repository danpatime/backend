package com.example.api.business.dto;

import com.example.api.account.entity.Location;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AddBusinessCommand(
        @NotNull
        Long requestMemberId,
        String businessName,
        Location location,
        List<Long> categoryIds,
        String representationName

) {
}
