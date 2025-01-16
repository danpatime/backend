package com.example.api.review.dto;

import java.time.LocalDateTime;

public record ReviewCommand(
        Long accountId,
        String businessName,
        Long businessId,
        LocalDateTime contractSrartTime,
        LocalDateTime contractEndTime,
        int reviewStarPoint,
        String reviewContent) {

    public ReviewCommand(Long accountId) {
        this(accountId, null, null, null, null, 0, null);
    }
}


