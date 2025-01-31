package com.example.api.aws.dto;

import jakarta.validation.constraints.NotBlank;

public record OldKeyRequest(@NotBlank String oldKey) {
}
