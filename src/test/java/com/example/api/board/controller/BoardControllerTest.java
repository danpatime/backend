package com.example.api.board.controller;

import com.example.api.board.controller.domain.response.Board;
import com.example.api.board.controller.domain.response.CategoryDTO;
import com.example.api.board.controller.domain.response.MyInfoDTO;
import com.example.api.global.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class BoardControllerTest extends BaseIntegrationTest {
    @Autowired BoardController boardController;

    @Test
    void getBoard(){
        List<CategoryDTO> expectedCategoryDTOList = new ArrayList<>();
        expectedCategoryDTOList.add(new CategoryDTO(category1.getCategoryId(), category1.getCategoryName()));
        expectedCategoryDTOList.add(new CategoryDTO(category2.getCategoryId(), category2.getCategoryName()));

        Board expectedBoard = new Board(
                new MyInfoDTO(
                "John Doe",
                "Johnny",
                30,
                "Male",
                "johndoe@example.com",
                "123-456-7890",
                innerCareerList,
                externalCareerList,
                possibleBoardList,
                flavoredCategoryList,
                3.5f,
                3
                ),
                expectedCategoryDTOList
        );

        Board boardByEmployeeId = boardController.findBoardByEmployeeId(1L);

        Assertions.assertEquals(expectedBoard, boardByEmployeeId);
    }
}