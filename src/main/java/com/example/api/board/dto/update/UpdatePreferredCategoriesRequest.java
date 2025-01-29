package com.example.api.board.dto.update;

import java.util.List;

public record UpdatePreferredCategoriesRequest(
        List<Long> categoryIds
) {
}
