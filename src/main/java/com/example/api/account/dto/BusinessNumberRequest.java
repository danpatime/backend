package com.example.api.account.dto;

import jakarta.validation.constraints.NotBlank;

public record BusinessNumberRequest(
        @NotBlank
        String businessRegistrationNumber,
        @NotBlank
        String businessName,
        @NotBlank
        String representationName,
        @NotBlank
        String businessOpenDate) {
}
