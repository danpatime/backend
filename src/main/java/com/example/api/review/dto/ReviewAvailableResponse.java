package com.example.api.review.dto;

import com.example.api.domain.Account;

public record ReviewAvailableResponse(
        Long contractId,
        Account employeeId
) {}

