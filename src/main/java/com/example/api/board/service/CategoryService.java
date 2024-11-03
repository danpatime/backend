package com.example.api.board.service;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.board.repository.CategoryRepository;
import com.example.api.domain.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll();
    }
}
