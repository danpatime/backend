package com.example.api.possbileboard.dto;

public record ExternalCareerResponse(
        Long externalCareerId,
        String careerName,
        String period
) {
}
