package com.example.api.board.repository;

import java.util.List;

public interface FlavoredCategoryRepositoryCustom {
    void saveAllCategoryIds(Long accountId, List<Long> categoryIds, List<Long> subCategoryIds);
}

