package com.example.api.auth.dto;

public record LoginSuccessResponse(
        String accessToken,
        String refreshToken,
        String userId,
        String userRole
) {
}