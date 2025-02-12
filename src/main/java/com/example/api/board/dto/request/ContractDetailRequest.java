package com.example.api.board.dto.request;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

public record ContractDetailRequest(
        Long id,
        String title,
        @EqualsAndHashCode.Include
        LocalDateTime startTime,
        @EqualsAndHashCode.Include
        LocalDateTime endTime
) {
}
