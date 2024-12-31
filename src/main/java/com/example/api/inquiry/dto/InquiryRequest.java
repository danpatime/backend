package com.example.api.inquiry.dto;

import org.springframework.lang.NonNull;

public record InquiryRequest(
        String inquiryType,
        String subInquiryType,
        String title,
        String content,
        @NonNull
        Long accountId
) {
}
