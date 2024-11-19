package com.example.api.contracts.dto;

import org.springframework.lang.NonNull;

public record AcceptSuggestCommand(
        @NonNull
        Long suggestId,
        @NonNull
        Long businessId,
        @NonNull
        Long employeeId
) {
}
