package com.example.api.employer.controller.dto;

import com.example.api.domain.Location;
import jakarta.validation.constraints.NotNull;

public record EmployerBusinessesRequest(
        @NotNull Long businessId,
        @NotNull String businessName,
        @NotNull Location businessLocation
) {
}