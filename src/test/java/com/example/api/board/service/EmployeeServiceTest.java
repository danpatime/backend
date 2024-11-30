package com.example.api.board.service;

import com.example.api.board.controller.domain.request.EmployeeIdRequest;
import com.example.api.global.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest extends BaseIntegrationTest {
    @Autowired EmployeeService employeeService;

    @Test
    @DisplayName("공고 공개 여부 변경")
    void changeOpenStatus(){
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(1L);
        Boolean result = employeeService.changeOpenStatus(employeeIdRequest , false);
        assertTrue(result);
    }
}