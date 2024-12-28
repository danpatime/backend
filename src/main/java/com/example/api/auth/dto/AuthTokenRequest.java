package com.example.api.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthTokenRequest(
        @NotBlank String accessToken,
        @NotBlank String refreshToken
) {
}