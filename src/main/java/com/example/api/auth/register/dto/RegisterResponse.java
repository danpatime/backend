package com.example.api.auth.register.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterResponse {
    private final String message;
    private final String loginId;
}
