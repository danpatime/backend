package com.example.api.board.repository;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryDTO, Long> {

    @Query("SELECT (c.categoryId, c.categoryName) FROM Category c")
    List<CategoryDTO> findAllCategories();
}
