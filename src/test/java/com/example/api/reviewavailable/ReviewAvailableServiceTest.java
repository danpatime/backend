//package com.example.api.reviewavailable;
//
//import com.example.api.account.repository.AccountRepository;
//import com.example.api.business.BusinessRepository;
//import com.example.api.contracts.ContractRepository;
//import com.example.api.domain.Account;
//import com.example.api.domain.Business;
//import com.example.api.domain.Contract;
//import com.example.api.domain.OfferEmployment;
//import com.example.api.reviewavailable.dto.ReviewAvailableCommand;
//import com.example.api.reviewavailable.dto.ReviewAvailableResponse;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class ReviewAvailableServiceTest {
//    @Autowired
//    private ReviewAvailableService reviewAvailableService;
//
//    @Autowired
//    private ContractRepository contractRepository;
//
//    @Autowired
//    private BusinessRepository businessRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    private Business business;
//    private Account employee1;
//    private Account employee2;
//
//    @BeforeEach
//    void setUp() {
//        contractRepository.deleteAll();
//        businessRepository.deleteAll();
//        accountRepository.deleteAll();
//
//        business = new Business();
//        business.setBusinessId(1L);
//        business.setBusinessName("Test Business");
//        business.setLocation("Seoul");
//        businessRepository.save(business);
//
//        employee1 = new Account();
//        employee1.setAccountId(1001L);
//        employee1.setName("John Doe");
//        employee1.setPhoneNumber("010-1234-5678");
//        accountRepository.save(employee1);
//
//        employee2 = new Account();
//        employee2.setAccountId(1002L);
//        employee2.setName("Jane Smith");
//        employee2.setPhoneNumber("010-8765-4321");
//        accountRepository.save(employee2);
//
//        OfferEmployment offerEmployment1 = new OfferEmployment();
//        offerEmployment1.setEmployee(employee1);
//        offerEmployment1.setBusiness(business);
//
//        Contract contract1 = new Contract(); // 완료된 계약 생성
//        contract1.setContractSucceeded(true);
//        contract1.setOfferEmployment(offerEmployment1);
//        contractRepository.save(contract1);
//
//        OfferEmployment offerEmployment2 = new OfferEmployment();
//        offerEmployment2.setEmployee(employee2);
//        offerEmployment2.setBusiness(business);
//
//        Contract contract2 = new Contract(); // 완료되지 않은 계약 생성
//        contract2.setContractSucceeded(false);
//        contract2.setOfferEmployment(offerEmployment2);
//        contractRepository.save(contract2);
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("완료된 계약이 있는 알바생 조회")
//    void getAvailableReviewTargets_ShouldReturnCompletedContracts() {
//        ReviewAvailableCommand command = new ReviewAvailableCommand(business.getBusinessId());
//        List<ReviewAvailableResponse> responses = reviewAvailableService.getAvailableReviewTargets(command);
//        assertNotNull(responses);
//        assertEquals(1, responses.size());
//        assertEquals(employee1.getAccountId(), responses.get(0).employeeId());
//        assertEquals(employee1.getName(), responses.get(0).employeeName());
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("가게 ID가 없을 때 빈 리스트 반환")
//    void getAvailableReviewTargets_ShouldReturnEmptyListForInvalidBusinessId() {
//        ReviewAvailableCommand command = new ReviewAvailableCommand(999L);
//        List<ReviewAvailableResponse> responses = reviewAvailableService.getAvailableReviewTargets(command);
//        assertNotNull(responses);
//        assertTrue(responses.isEmpty());
//    }
//}
