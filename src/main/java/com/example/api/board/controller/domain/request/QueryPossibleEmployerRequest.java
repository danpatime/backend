package com.example.api.board.controller.domain.request;

import java.time.LocalDateTime;

public record QueryPossibleEmployerRequest(
        String name,
        Integer age,
        LocalDateTime possibleStartDateTime,
        LocalDateTime possibleEndDateTime
) {
}
