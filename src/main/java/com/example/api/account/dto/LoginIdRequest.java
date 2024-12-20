package com.example.api.account.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginIdRequest(@NotBlank String loginId) {
}
