package com.example.api.board.controller;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.board.dto.response.Board;
import com.example.api.board.dto.response.CategoryDTO;
import com.example.api.board.dto.response.MyInfoDTO;
import com.example.api.board.service.BoardService;
import com.example.api.board.service.CategoryService;
import com.example.api.board.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;

    @GetMapping("/api/v1/possible-board/form")
    public Board findBoardByEmployeeId(@AuthenticationPrincipal final Long employeeId) {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(employeeId);
        MyInfoDTO myInfoById = boardService.findMyInfoById(employeeIdRequest);
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        return new Board(myInfoById, categoryList);
    }

    @PostMapping("/api/v1")
    public ResponseEntity changeOpenStatus(@AuthenticationPrincipal final Long employeeId, @RequestParam ("openStatus") Boolean openStatus) {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(employeeId);
        boolean updated = employeeService.changeOpenStatus(employeeIdRequest, openStatus);
        if (updated) {
            return ResponseEntity.ok("사용자 정보가 성공적으로 업데이트되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/api/v1/possible-board/submit")
    public ResponseEntity submitBoard(@AuthenticationPrincipal final Long employeeId, @RequestBody MyInfoDTO myInfo) {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(employeeId);
        employeeService.updateUserInfo(employeeIdRequest, myInfo);
        MyInfoDTO myInfoById = boardService.findMyInfoById(employeeIdRequest);
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(new Board(myInfoById, categoryList));
    }
}
