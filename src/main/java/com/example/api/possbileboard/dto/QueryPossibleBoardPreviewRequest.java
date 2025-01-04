package com.example.api.possbileboard.dto;

import java.time.LocalDateTime;

public record QueryPossibleBoardPreviewRequest(
        Integer age,
        String name,
        LocalDateTime startWorkTime,
        LocalDateTime endWorkTime
) {
}
