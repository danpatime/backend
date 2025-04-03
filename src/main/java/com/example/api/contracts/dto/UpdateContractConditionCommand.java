package com.example.api.contracts.dto;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record UpdateContractConditionCommand(
        @NonNull
        Long contractId,
        LocalDateTime suggestStartDateTime,
        LocalDateTime suggestEndDateTime,
        Integer suggestHourlyPayment
) {
}
