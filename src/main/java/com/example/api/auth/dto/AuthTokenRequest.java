package com.example.api.auth.dto;

public record AuthTokenRequest(String accessToken, String refreshToken) {
}
