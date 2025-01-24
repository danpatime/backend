package com.example.api.inquiry.controller;

import com.example.api.domain.Account;
import com.example.api.domain.Inquiry;
import com.example.api.inquiry.InquiryService;
import com.example.api.inquiry.dto.InquiryRequest;
import com.example.api.inquiry.dto.InquiryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support")
public class InquiryController {
    private final InquiryService inquiryService;

    @PostMapping("/inquiry")
    public ResponseEntity<InquiryResponse> createInquiry(
            @AuthenticationPrincipal final Long memberId,
            @RequestBody final InquiryRequest inquiryRequest
    ) {
        final Inquiry inquiry = inquiryService.saveInquiry(
                inquiryRequest,
                memberId
        );
        final InquiryResponse inquiryResponse = mapToResponse(inquiry);
        return ResponseEntity.ok(inquiryResponse);
    }

    @GetMapping("/my-inquiries")
    public ResponseEntity<List<InquiryResponse>> getMyInquiries(
            @AuthenticationPrincipal final Long memberId
    ) {
        final List<InquiryResponse> inquiryResponses = inquiryService.getInquiriesByAccountId(memberId);
        return ResponseEntity.ok(inquiryResponses);
    }

    private InquiryResponse mapToResponse(Inquiry inquiry) {
        return new InquiryResponse(
                inquiry.getInquiryId(),
                inquiry.getInquiryType(),
                inquiry.getSubInquiryType(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getInquiryStatus().name(),
                inquiry.getAnswerDate(),
                inquiry.getCreatedBy()
        );
    }
}
