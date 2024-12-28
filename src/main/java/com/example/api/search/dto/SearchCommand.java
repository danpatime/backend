package com.example.api.search.dto;

public record SearchResponse(
        final String name,
        final String sex,
        final Integer minAge,
        final Integer maxAge,
        final Float minStarPoint,
        final Float maxStarPoint
) {}

