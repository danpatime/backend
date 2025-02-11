package com.example.api.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlavoredCategoryRepositoryImpl implements FlavoredCategoryRepositoryCustom {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public void saveAllCategoryIds(Long accountId, List<Long> categoryIds, List<Long> subCategoryIds) {
        if (categoryIds.isEmpty() || subCategoryIds.isEmpty()) return;

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO FLAVORED_CATEGORY (CATEGORY_ID, SUB_CATEGORY_ID, EMPLOYEE_ID) VALUES ");

        for (int i = 0; i < categoryIds.size(); i++) {
            sql.append("(")
                    .append(categoryIds.get(i)).append(", ")
                    .append(subCategoryIds.get(i)).append(", ")
                    .append(accountId)
                    .append("),");
        }

        // 마지막 쉼표 제거
        sql.setLength(sql.length() - 1);

        // 실행
        entityManager.createNativeQuery(sql.toString()).executeUpdate();
    }
}

