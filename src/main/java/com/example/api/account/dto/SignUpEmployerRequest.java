package com.example.api.account.dto;

import com.example.api.account.entity.Location;
import com.example.api.account.entity.Nationality;
import com.example.api.account.entity.UserRole;
import com.example.api.global.config.resolver.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpEmployerRequest(
    @NotBlank
    String loginId,    // 로그인 id
    @NotBlank
    String password,    // 비밀번호
    @ValidEmail
    String email,   // 이메일
    @NotBlank
    String businessRegistrationNumber,  // 사업자 번호
    @NotBlank
    String businessName,    // 회사명
    @NotBlank
    String representationName,  // 대표명
    @NotBlank
    String businessOpenDate,    // 개업연월일
    @NotNull
    Location location,
    @NotNull
    Nationality nationality,    // 국적
    @NotNull
    UserRole role,     // 권한
    @NotBlank
    String phoneNumber   // 휴대폰 번호
) {
}