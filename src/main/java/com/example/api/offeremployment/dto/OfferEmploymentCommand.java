package com.example.api.offeremployment.dto;

import java.time.LocalDateTime;

public record OfferEmploymentCommand(
        Long employeeId,
        Long businessId,
        int suggestHourlyPay,
        LocalDateTime suggestStartTime,
        LocalDateTime suggestEndTime
) {}
