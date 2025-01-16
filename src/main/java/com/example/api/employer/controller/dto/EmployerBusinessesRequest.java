package com.example.api.employer.controller.dto;

import jakarta.validation.constraints.NotNull;

public record EmployerBusinessesRequest(
        @NotNull String businessName,
        @NotNull String businessLocation
) {
}