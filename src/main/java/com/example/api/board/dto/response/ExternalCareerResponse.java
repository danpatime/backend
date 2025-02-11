package com.example.api.board.dto.response;

public record ExternalCareerResponse(
        Long externalCareerId,
        SubCategoryResponse subCategory,
        Integer workCount
) {
}
