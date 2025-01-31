package com.example.api.aws.dto;

import jakarta.validation.constraints.NotNull;

public record UploadProfileResponse(@NotNull String path) {
}
