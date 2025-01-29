package com.example.api.board.dto.response;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public record WorkHourResponse(
        Long id,
        @EqualsAndHashCode.Include
        LocalDateTime startTime,
        @EqualsAndHashCode.Include
        LocalDateTime endTime
) {
}
