package com.example.api.board.dto.response;

public record FlavoredCategoryResponse(
        Long categoryId,
        String categoryName,
        Long subCategoryIdm,
        String subCategoryName
) {
};