package com.example.api.inquiry.dto;

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
        String processStatus,
        LocalDateTime answerDate,
        @NonNull
        Long accountId
) {
}
