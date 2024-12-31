package com.example.api.inquiry.dto;

import com.example.api.domain.Account;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record InquiryResponse(
        @NonNull
        Long inquiryId,
        String inquiryType,
        String subInquiryType,
        String title,
        String content,
        String inquiryStatus,
        LocalDateTime answerDate,
        @NonNull
        Account createdBy
) {
}
