package com.example.api.inquiry;

import com.example.api.domain.Account;
import com.example.api.domain.Inquiry;
import com.example.api.global.BaseIntegrationTest;
import com.example.api.inquiry.dto.InquiryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

class InquiryControllerTest extends BaseIntegrationTest {
    @Autowired
    private InquiryController inquiryController;

    @Test
    void getMyInquiries() {
        Account account = new Account();
        account.setAccountId(1L);
        Inquiry inquiry1 = new Inquiry();
        inquiry1.setInquiryId(1L);
        inquiry1.setInquiryType("General");
        inquiry1.setTitle("Test Inquiry 1");
        inquiry1.setCreatedBy(account);

        Inquiry inquiry2 = new Inquiry();
        inquiry2.setInquiryId(2L);
        inquiry2.setInquiryType("Support");
        inquiry2.setTitle("Test Inquiry 2");
        inquiry2.setCreatedBy(account);

        List<InquiryResponse> expectedResponses = List.of(
                new InquiryResponse(
                        inquiry1.getInquiryId(),
                        inquiry1.getInquiryType(),
                        inquiry1.getSubInquiryType(),
                        inquiry1.getTitle(),
                        inquiry1.getContent(),
                        inquiry1.getInquiryStatus().name(),
                        inquiry1.getAnswerDate(),
                        inquiry1.getCreatedBy()
                ),
                new InquiryResponse(
                        inquiry2.getInquiryId(),
                        inquiry2.getInquiryType(),
                        inquiry2.getSubInquiryType(),
                        inquiry2.getTitle(),
                        inquiry2.getContent(),
                        inquiry2.getInquiryStatus().name(),
                        inquiry2.getAnswerDate(),
                        inquiry2.getCreatedBy()
                )
        );

        ResponseEntity<List<InquiryResponse>> response = inquiryController.getMyInquiries(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<InquiryResponse> actualResponses = response.getBody();
        Assertions.assertNotNull(actualResponses);
        Assertions.assertEquals(expectedResponses.size(), actualResponses.size());

        for (int i = 0; i < expectedResponses.size(); i++) {
            Assertions.assertEquals(expectedResponses.get(i).getInquiryId(), actualResponses.get(i).getInquiryId());
            Assertions.assertEquals(expectedResponses.get(i).getInquiryType(), actualResponses.get(i).getInquiryType());
            Assertions.assertEquals(expectedResponses.get(i).getTitle(), actualResponses.get(i).getTitle());
        }
    }
}
