package com.example.api.board.dto.response;

import java.time.LocalDateTime;

public record InternalCareerResponse(
        Long contractId,
        String businessName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
