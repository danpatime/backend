package com.example.api.suggest.controller.dto.request;

import java.time.LocalDateTime;

public record OfferEmploymentDetailRequest(
        String name,
        String businessName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
