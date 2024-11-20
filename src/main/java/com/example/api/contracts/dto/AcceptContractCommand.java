package com.example.api.contracts.dto;

import jakarta.validation.constraints.NotNull;

public record AcceptContractCommand(
        @NotNull
        Long contractId
) {
}
