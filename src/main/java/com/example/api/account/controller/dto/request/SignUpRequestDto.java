package com.example.api.account.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(
    @NotBlank
    String loginId,

    @NotBlank
    String password,

    @NotBlank
    String name,

    @NotBlank
    String nickname,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String nationality,

    @NotBlank
    String phoneNumber
) {
}
