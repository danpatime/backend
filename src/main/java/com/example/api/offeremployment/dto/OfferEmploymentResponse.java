package com.example.api.offeremployment.dto;

import com.example.api.domain.OfferEmployment;
import com.example.api.domain.ProposalStatus;

public record OfferEmploymentResponse(
        Long suggestId,
        ProposalStatus status
) {
    public static OfferEmploymentResponse fromEntity(OfferEmployment offerEmployment) {
        return new OfferEmploymentResponse(
                offerEmployment.getSuggestId(),
                offerEmployment.getStatus()
        );
    }
}

