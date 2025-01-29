package com.example.api.auth.dto;

import jakarta.servlet.http.Cookie;

import java.util.Map;

public record LoginSuccessResponse(
        Cookie refreshTokenCookie,
        Map<String, String> responseBody
) {
}