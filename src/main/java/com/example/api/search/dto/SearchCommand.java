package com.example.api.search.dto;

import java.time.LocalDateTime;

public record SearchCommand(
        String category,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}




