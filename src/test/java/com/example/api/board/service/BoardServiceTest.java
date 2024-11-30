package com.example.api.board.service;

import com.example.api.board.controller.domain.response.MyInfoDTO;
import com.example.api.global.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class BoardServiceTest extends BaseIntegrationTest {
    @Autowired BoardService boardService;

    @Test
    @DisplayName("나의 이력서 정보 조회")
    void MyInfoCheck(){
        MyInfoDTO expectedMyInfoDTO = new MyInfoDTO(
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
        );

        MyInfoDTO findEmployeeInfo = boardService.findMyInfoById(1L);
        Assertions.assertEquals(expectedMyInfoDTO, findEmployeeInfo);
    }
}