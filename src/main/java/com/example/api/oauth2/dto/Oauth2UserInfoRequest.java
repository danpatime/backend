package com.example.api.oauth2.dto;

import com.example.api.account.entity.UserRole;
import com.example.api.global.config.resolver.ValidEmail;
import jakarta.validation.constraints.NotBlank;

public record Oauth2UserInfoRequest(@NotBlank String name, @ValidEmail String email, @NotBlank UserRole role) {
}
