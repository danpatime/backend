package com.example.api.employer.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.board.controller.domain.request.EmployeeIdRequest;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.employer.controller.dto.EmployerBusinessesRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployerServiceTest {
    @Autowired
    private EmployerService employerService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BusinessRepository businessRepository;
    private List<EmployerBusinessesRequest> businessesList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Account account = new Account();
        accountRepository.save(account);
        Business business1 = new Business(account, "가게명1", "위치1");
        Business business2 = new Business(account, "가게명2", "위치2");
        Business business3 = new Business(account, "가게명3", "위치3");
        businessRepository.save(business1);
        businessRepository.save(business2);
        businessRepository.save(business3);
        businessesList.add(new EmployerBusinessesRequest(business1.getBusinessName(), business1.getLocation()));
        businessesList.add(new EmployerBusinessesRequest(business2.getBusinessName(), business2.getLocation()));
        businessesList.add(new EmployerBusinessesRequest(business3.getBusinessName(), business3.getLocation()));
    }

    @Test
    void testGetBusinessesByOwnerId(){
        List<EmployerBusinessesRequest> employerBusinessList = employerService.getEmployerBusinessList(new EmployeeIdRequest(1L));
        Assertions.assertThat(employerBusinessList).isEqualTo(businessesList);
    }
}