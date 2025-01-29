package com.example.api.board.dto.request;

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
