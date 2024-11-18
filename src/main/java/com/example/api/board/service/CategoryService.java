package com.example.api.board.service;

import com.example.api.board.controller.domain.response.CategoryDTO;
import com.example.api.domain.repository.CategoryRepository;
import com.example.api.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryDTO(category.getCategoryId(), category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
