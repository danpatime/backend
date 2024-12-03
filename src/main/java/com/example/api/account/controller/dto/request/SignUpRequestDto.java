package com.example.api.account.controller.dto.request;

import com.example.api.account.domain.Nationality;
import com.example.api.account.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    Nationality nationality,

    @NotNull
    UserRole role,

    @NotBlank
    String phoneNumber
) {
}
