package com.example.api.offeremployment.dto;

import com.example.api.domain.OfferEmployment;

public record OfferEmploymentResponse(
        Long suggestId,
        boolean success,
        String message
) {
    public static OfferEmploymentResponse fromEntity(OfferEmployment offerEmployment) {
        return new OfferEmploymentResponse(
                offerEmployment.getSuggestId(),
                offerEmployment.isSuggestSucceeded(),
                offerEmployment.isSuggestSucceeded() ? "Offer succeeded" : "Offer pending"
        );
    }
}

