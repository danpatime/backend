package com.example.api.reviewavailable.dto;

import com.example.api.domain.Account;
import com.example.api.domain.Contract;

public record ReviewAvailableResponse(
        Long employeeId,
        String employeeName
) {
    public static ReviewAvailableResponse fromContract(Contract contract) {
        final Account employee = contract.getOfferEmployment().getEmployee();
        return new ReviewAvailableResponse(
                employee.getAccountId(),
                employee.getName()
        );
    }
}

