package com.example.api.inquiry.dto;

import com.example.api.domain.Account;
import org.springframework.lang.NonNull;

public record InquiryRequest(
        @NonNull
        String inquiryType,
        String subInquiryType,
        String title,
        String content
) {

    public InquiryCommand toCommand(final Long memberId) {
        return new InquiryCommand(
                null,
                inquiryType,
                subInquiryType,
                title,
                content,
                "WAITING",
                null,
                memberId
        );
    }
}
