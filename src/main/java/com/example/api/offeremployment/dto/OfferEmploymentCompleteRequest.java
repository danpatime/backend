package com.example.api.offeremployment.dto;

import jakarta.validation.constraints.NotNull;

public record OfferEmploymentCompleteRequest(
        @NotNull Long suggestId,
        @NotNull Long employeeId
) {
}