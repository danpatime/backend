package com.example.api.offeremployment.dto;

import jakarta.validation.constraints.NotNull;

public record StarPointAndWorkCountRequest(
        @NotNull float starPoint,
        @NotNull int workCount
) {
}
