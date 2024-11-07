package com.example.api.board.service;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.global.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class CategoryServiceTest extends BaseIntegrationTest {
    @Autowired CategoryService categoryService;

    @Test
    @DisplayName("모든 카테고리 조회")
    void CategoryCheck(){
        List<CategoryDTO> expectedCategoryDTO = new ArrayList<>();
        expectedCategoryDTO.add(new CategoryDTO(category1.getCategoryId(), category1.getCategoryName()));
        expectedCategoryDTO.add(new CategoryDTO(category2.getCategoryId(), category2.getCategoryName()));

        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        Assertions.assertEquals(expectedCategoryDTO, allCategories);
    }

}