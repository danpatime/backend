package com.example.api.account.dto;

import com.example.api.account.entity.Nationality;
import com.example.api.account.entity.UserRole;
import com.example.api.global.config.resolver.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpEmployeeRequest(
    @NotBlank
    String loginId,
    @NotBlank
    String password,
    @NotBlank
    String name,
    @NotBlank
    String nickname,
    @ValidEmail
    String email,
    @NotNull
    Nationality nationality ,
    @NotNull
    UserRole role,
    @NotBlank
    String phoneNumber,
    @NotNull
    Boolean emailReceivable
) {
}