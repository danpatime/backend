package com.example.api.board.service;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.domain.repository.CategoryRepository;
import com.example.api.domain.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryDTO(category.getCategoryId(), category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
