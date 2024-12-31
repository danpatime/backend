package com.example.api.inquiry.dto;

import com.example.api.domain.Account;
import org.springframework.lang.NonNull;

public record InquiryRequest(
        @NonNull
        String inquiryType,
        String subInquiryType,
        String title,
        String content,
        @NonNull
        Account createdBy
) {

    public InquiryCommand toCommand(final Account account) {
        return new InquiryCommand(
                null,
                inquiryType,
                subInquiryType,
                title,
                content,
                "WAITING",
                null,
                account
        );
    }
}
