package com.example.api.contracts.dto;

import java.time.LocalDateTime;

public record UpdateContractConditionRequest(
        LocalDateTime suggestStartDateTime,
        LocalDateTime suggestEndDateTime,
        Integer suggestHourlyPayment
) {
    public UpdateContractConditionCommand toCommand(final Long contractId) {
        return new UpdateContractConditionCommand(
                contractId,
                this.suggestStartDateTime,
                this.suggestEndDateTime,
                this.suggestHourlyPayment
        );
    }
}
