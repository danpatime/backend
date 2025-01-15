package com.example.api.search.dto;

import java.time.LocalDateTime;

public record SearchRequest(
        String category,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}

