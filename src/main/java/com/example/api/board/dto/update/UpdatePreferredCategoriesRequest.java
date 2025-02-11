package com.example.api.board.dto.update;

import java.util.List;

public record UpdatePreferredCategoriesRequest(
        List<PreferredCategoryIdsRequest> categoryIds
) {
    public record PreferredCategoryIdsRequest(
            Long categoryId,
            Long subCategoryId
    ){}
}
