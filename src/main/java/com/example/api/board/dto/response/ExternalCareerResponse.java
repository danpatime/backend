package com.example.api.board.dto.response;

import com.example.api.domain.Category;

public record ExternalCareerResponse(
        Long externalCareerId,
        Category category,
        Integer workCount
) {
}
