package com.example.api.contracts.dto;

import org.springframework.lang.NonNull;

public record AcceptContractCommand(
        @NonNull
        Long contractId,
        Long employeeId
) {
}
