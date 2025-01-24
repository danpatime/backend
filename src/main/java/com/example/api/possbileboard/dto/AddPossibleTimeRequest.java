package com.example.api.possbileboard.dto;

import com.example.api.possbileboard.dto.AddPossibleTimeCommand.PossibleTimeRange;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AddPossibleTimeRequest(
        List<PossibleTimeForm> possibleTimes
) {

    public AddPossibleTimeCommand toCommand(final Long requestMemberId) {
        final List<PossibleTimeRange> possibleTimeRanges = this.possibleTimes.stream()
                .map(possibleTimeForm -> new PossibleTimeRange(possibleTimeForm.startTime, possibleTimeForm.endTime))
                .collect(Collectors.toList());
        return new AddPossibleTimeCommand(requestMemberId, possibleTimeRanges);
    }

    record PossibleTimeForm(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
    }
}
