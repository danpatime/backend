package com.example.api.inquiry;

import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.InquiryCommand;
import com.example.api.inquiry.dto.InquiryRequest;
import com.example.api.inquiry.dto.InquiryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InquiryServiceTest {

    @MockBean
    private InquiryRepository inquiryRepository;

    @Autowired
    private InquiryService inquiryService;

    private InquiryRequest inquiryRequest;
    private InquiryCommand inquiryCommand;
    private Inquiry inquiry;

    public InquiryServiceTest() {
        inquiryRequest = new InquiryRequest("Technical Support", "Billing Issue", "How to pay?", "I have a question about paying my bill.");
        inquiry = new Inquiry(1L, "Technical Support", "Billing Issue", "How to pay?", "I have a question about paying my bill.", Inquiry.InquiryStatus.WAITING, null);
    }

    @Test
    @DisplayName("새로운 문의사항을 생성할 수 있어야 한다")
    void shouldCreateInquiry() {
        when(inquiryRepository.save(Mockito.any(Inquiry.class))).thenReturn(inquiry);
        InquiryResponse response = inquiryService.saveInquiry(inquiryRequest, 1L);
        assertNotNull(response);
        assertEquals("Technical Support", response.inquiryType());
        assertEquals("Billing Issue", response.subInquiryType());
        assertEquals("How to pay?", response.title());
        verify(inquiryRepository, times(1)).save(Mockito.any(Inquiry.class));
    }

    @Test
    @DisplayName("특정 사용자의 모든 문의사항을 조회할 수 있어야 한다")
    void shouldGetInquiriesByAccountId() {
        when(inquiryRepository.findByCreatedBy(1L)).thenReturn(Collections.singletonList(inquiry));
        List<InquiryResponse> responses = inquiryService.getInquiriesByAccountId(1L);
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Technical Support", responses.get(0).inquiryType());
        assertEquals("Billing Issue", responses.get(0).subInquiryType());
        verify(inquiryRepository, times(1)).findByCreatedBy(1L);
    }
}



