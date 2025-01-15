package com.example.api.possbileboard.dto;

import java.time.LocalDateTime;

public record PossibleDetails(
        String name,
        Integer age,
        String email,
        String phoneNumber,
        LocalDateTime recentlyUpdatedTime,
        LocalDateTime possibleStartTime,
        LocalDateTime possibleEndTime,
        Long contractCount,
        Float starPoint
) {
}
