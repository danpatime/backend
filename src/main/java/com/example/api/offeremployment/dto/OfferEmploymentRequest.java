package com.example.api.offeremployment.dto;

import java.time.LocalDateTime;

public record OfferEmploymentRequest(
        Long employeeId,
        Long businessId,
        int suggestHourlyPay,
        LocalDateTime suggestStartTime,
        LocalDateTime suggestEndTime
) {
    public OfferEmploymentCommand toCommand() {
        return new OfferEmploymentCommand(
                this.employeeId,
                this.businessId,
                this.suggestHourlyPay,
                this.suggestStartTime,
                this.suggestEndTime
        );
    }
}
