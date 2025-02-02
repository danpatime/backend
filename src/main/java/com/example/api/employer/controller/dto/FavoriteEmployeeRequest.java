package com.example.api.employer.controller.dto;

public record FavoriteEmployeeRequest(
        Long employeeId,
        Long employerId
) {
}
