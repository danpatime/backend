package com.example.api.contracts.dto;

import java.time.LocalDateTime;
import org.springframework.lang.NonNull;

public record UpdateContractConditionCommand(
        @NonNull
        Long contractId,
        LocalDateTime suggestStartDateTime,
        LocalDateTime suggestEndDateTime,
        Integer suggestHourlyPayment
) {
}
