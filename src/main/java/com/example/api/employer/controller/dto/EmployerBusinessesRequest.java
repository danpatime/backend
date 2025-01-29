package com.example.api.employer.controller.dto;

import com.example.api.business.domain.BusinessLocation;
import jakarta.validation.constraints.NotNull;

public record EmployerBusinessesRequest(
        @NotNull String businessName,
        @NotNull BusinessLocation businessLocation
) {
}