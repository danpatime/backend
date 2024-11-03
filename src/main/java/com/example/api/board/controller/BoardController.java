package com.example.api.board.controller;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.board.controller.domain.FormDTO;
import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.board.service.BoardService;
import com.example.api.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;

    public FormDTO findById(long id) {
        MyInfoDTO myInfoById = boardService.findMyInfoById(id);
        List<CategoryDTO> categoryList = categoryService.findAll();
        return new FormDTO(myInfoById, categoryList);
    }
}
