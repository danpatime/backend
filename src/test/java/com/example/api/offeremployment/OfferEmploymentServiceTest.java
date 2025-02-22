//package com.example.api.offeremployment;
//
//import com.example.api.domain.Location;
//import com.example.api.account.entity.Nationality;
//import com.example.api.account.entity.UserRole;
//import com.example.api.account.repository.AccountRepository;
//import com.example.api.business.BusinessRepository;
//import com.example.api.domain.Account;
//import com.example.api.domain.Business;
//import com.example.api.domain.OfferEmployment;
//import com.example.api.domain.repository.OfferEmploymentRepository;
//import com.example.api.offeremployment.dto.OfferEmploymentRequest;
//import com.example.api.offeremployment.dto.OfferEmploymentResponse;
//import jakarta.annotation.PostConstruct;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class OfferEmploymentServiceTest {
//
//    @Autowired
//    private OfferEmploymentService offerEmploymentService;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private BusinessRepository businessRepository;
//
//    @Autowired
//    private OfferEmploymentRepository offerEmploymentRepository;
//
//    @PostConstruct
//    void setUp() {
//        offerEmploymentRepository.deleteAll();
//        businessRepository.deleteAll();
//        accountRepository.deleteAll();
//
//        Account employee = new Account(
//                "employee01", "password123", "Alice", "nickname1", "010-1234-5678",
//                "alice@example.com", Nationality.KOREAN, List.of(UserRole.ROLE_EMPLOYEE), true
//        );
//        accountRepository.save(employee);
//
//        Account employer = new Account(
//                "employer01", "password456", "Bob", "nickname2", "010-9876-5432",
//                "bob@example.com", Nationality.KOREAN, List.of(UserRole.ROLE_EMPLOYER), true
//        );
//        accountRepository.save(employer);
//
//        Business business = new Business(
//                "My Coffee Shop",
//                new Location(),
//                "Bob",
//                employer,
//                LocalDate.of(2020, 1, 1),
//                "123-45-67890"
//        );
//        businessRepository.save(business);
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("정상적으로 고용 제안을 보낼 수 있다")
//    void sendOfferEmployment_ShouldSucceed() {
//        OfferEmploymentRequest request = new OfferEmploymentRequest(
//                1L,
//                1L,
//                15000,
//                LocalDateTime.of(2025, 1, 1, 9, 0),
//                LocalDateTime.of(2025, 1, 1, 18, 0)
//        );
//
//        OfferEmploymentResponse response = offerEmploymentService.sendOfferEmployment(request);
//
//        assertNotNull(response);
//        assertTrue(response.success());
//        assertEquals("Offer succeeded", response.message());
//
//        Optional<OfferEmployment> savedOfferEmployment = offerEmploymentRepository.findAll().stream().findFirst();
//        assertTrue(savedOfferEmployment.isPresent());
//        OfferEmployment offerEmployment = savedOfferEmployment.get();
//        assertEquals(1L, offerEmployment.getEmployee().getAccountId());
//        assertEquals(1L, offerEmployment.getBusiness().getBusinessId());
//        assertEquals(15000, offerEmployment.getSuggestHourlyPay());
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("잘못된 알바 ID로 고용 제안을 보낼 경우 실패한다")
//    void sendOfferEmployment_InvalidEmployee_ShouldFail() {
//        OfferEmploymentRequest request = new OfferEmploymentRequest(
//                999L,
//                1L,
//                15000,
//                LocalDateTime.of(2025, 1, 1, 9, 0),
//                LocalDateTime.of(2025, 1, 1, 18, 0)
//        );
//
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> offerEmploymentService.sendOfferEmployment(request)
//        );
//        assertEquals("Account not found with ID: 999", exception.getMessage());
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("잘못된 비즈니스 ID로 고용 제안을 보낼 경우 실패한다")
//    void sendOfferEmployment_InvalidBusiness_ShouldFail() {
//        OfferEmploymentRequest request = new OfferEmploymentRequest(
//                1L,
//                999L,
//                15000,
//                LocalDateTime.of(2025, 1, 1, 9, 0),
//                LocalDateTime.of(2025, 1, 1, 18, 0)
//        );
//
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> offerEmploymentService.sendOfferEmployment(request)
//        );
//        assertEquals("Business not found with ID: 999", exception.getMessage());
//    }
//}
//
