package com.example.api.account.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginIdRequest(@NotBlank String loginId) {
}
