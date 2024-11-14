package com.example.api.contracts.dto;

import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

public record AddContractReviewCommand(
        @NonNull
        Long contractId,
        String reviewContent,
        @NonNull
        @Range(min = 0L, max = 5L)
        Integer starPoint
) {
}
