package com.example.api.account.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterResponseDto {
    private final String message;
    private final String loginId;
}
