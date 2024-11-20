package com.example.api.contracts.dto;

public record AddContractReviewRequest(
        String reviewContent,
        Integer starPoint
) {
    public AddContractReviewCommand toCommand(final Long contractId) {
        return new AddContractReviewCommand(contractId, reviewContent, starPoint);
    }
}
