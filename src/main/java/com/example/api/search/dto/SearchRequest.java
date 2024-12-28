package com.example.api.search.dto;

public record JobResponse(
        final String name,
        final String sex,
        final int age,
        final float starPoint,
        final int workCount
) {}
