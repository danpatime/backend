package com.example.api.possbileboard.dto;

import java.time.LocalDateTime;

public record InternalCareerResponse(
        Long contractId,
        Integer hourlyPayment,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
