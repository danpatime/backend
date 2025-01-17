package com.example.api.employer.controller.dto;

import com.example.api.account.entity.Location;
import jakarta.validation.constraints.NotNull;

public record EmployerBusinessesRequest(
        @NotNull String businessName,
        @NotNull Location businessLocation
) {
}