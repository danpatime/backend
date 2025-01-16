package com.example.api.inquiry;

<<<<<<< HEAD
<<<<<<< HEAD
import com.example.api.account.repository.AccountRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.InquiryRequest;
import com.example.api.inquiry.dto.InquiryResponse;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class InquiryServiceTest {
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    void setUp() {
        accountRepository.deleteAll();
        inquiryRepository.deleteAll();

        Account account = new Account();
        account.setAccountId(1L);
        account.setName("Alice");
        account.setEmail("alice@example.com");
        account.setAge(25);
        account.setSex("F");
        account.setPhoneNumber("010-1234-5678");
        account.setProfileImage("user-uploads/1/profile.png");
        account.setStarPoint(4.5f);
        account.setWorkCount(10);
        account.setOpenStatus(true);
        account.setDeleted(false);
        accountRepository.save(account);
    }

    @Test
    @DisplayName("문의 저장 및 저장된 문의 반환 테스트")
    void saveInquiry_shouldSaveAndReturnInquiry() {
        Account account = accountRepository.findById(1L).orElseThrow();
        InquiryRequest inquiryRequest = new InquiryRequest(
                "General",
                "Question",
                "Test Title",
                "Test Content",
                account
        );
        Inquiry result = inquiryService.saveInquiry(inquiryRequest, account);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
    }

    @Test
    @DisplayName("계정 ID로 문의 조회 테스트")
    void getInquiriesByAccountId_shouldReturnListOfInquiries() {
        Account account = accountRepository.findById(1L).orElseThrow();
        Inquiry inquiry1 = new Inquiry();
        inquiry1.setInquiryId(1L);
        inquiry1.setCreatedBy(account);
        inquiry1.setInquiryType("General");
        inquiry1.setSubInquiryType("Question");
        inquiry1.setTitle("Inquiry 1");
        inquiry1.setContent("Content 1");
        inquiry1.setInquiryStatus(Inquiry.InquiryStatus.WAITING);
        inquiry1.setAnswerDate(LocalDateTime.now());
        inquiryRepository.save(inquiry1);

        Inquiry inquiry2 = new Inquiry();
        inquiry2.setInquiryId(2L);
        inquiry2.setCreatedBy(account);
        inquiry2.setInquiryType("General");
        inquiry2.setSubInquiryType("Question");
        inquiry2.setTitle("Inquiry 2");
        inquiry2.setContent("Content 2");
        inquiry2.setInquiryStatus(Inquiry.InquiryStatus.COMPLETED);
        inquiry2.setAnswerDate(LocalDateTime.now());
        inquiryRepository.save(inquiry2);
        List<InquiryResponse> result = inquiryService.getInquiriesByAccountId(account.getAccountId());
        assertThat(result.get(0).title()).isEqualTo("Inquiry 1");
        assertThat(result.get(1).title()).isEqualTo("Inquiry 2");
    }

    @Test
    @DisplayName("문의 명령을 엔티티로 매핑 테스트")
    void mapToInquiry_shouldMapCommandToInquiry() {
        Account account = accountRepository.findById(1L).orElseThrow();
        InquiryRequest inquiryRequest = new InquiryRequest(
                "General",
                "Question",
                "Test Title",
                "Test Content",
                account
        );
        Inquiry inquiry = inquiryService.saveInquiry(inquiryRequest, account);
        assertThat(inquiry).isNotNull();
        assertThat(inquiry.getTitle()).isEqualTo("Test Title");
    }
=======
public class InquiryServiceController {
>>>>>>> 2f6b5cc (#53 feat(ReviewCommand): DTO 작성)
=======
public class InquiryServiceTest {
>>>>>>> 0ff3ba1 (#53 feat(ReviewService): 서비스 코드 구현)
}
