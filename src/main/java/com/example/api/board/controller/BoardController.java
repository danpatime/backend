package com.example.api.board.controller;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.board.controller.domain.Board;
import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.board.service.BoardService;
import com.example.api.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;

    @GetMapping("/api/v1/possible-board/form/{employeeId}")
    public Board findBoardByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        MyInfoDTO myInfoById = boardService.findMyInfoById(employeeId);
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        return new Board(myInfoById, categoryList);
    }
}
