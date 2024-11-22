package com.example.api.possbileboard.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AddPossibleTimeCommand(
        Long requestMemberId,
        List<PossibleTimeRange> possibleTimes
) {
    public record PossibleTimeRange(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
    }
}
