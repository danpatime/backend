package com.example.api.search.dto;

public record SearchResponse(
        String name,
        String sex,
        int age,
        float starPoint,
        int workCount
) {}
