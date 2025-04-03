package com.example.api.review.dto;

public record ReviewAvailableResponse(
        Long contractId,
        Long employeeId,
        String employeeName
) {
}

