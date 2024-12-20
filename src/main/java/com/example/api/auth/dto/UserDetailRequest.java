package com.example.api.auth.dto;

import com.example.api.account.domain.UserRole;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record UserDetailRequest(@NotNull Long userId, @NotNull Collection<UserRole> authorities) {
}
