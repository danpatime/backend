package com.example.api.inquiry;

import com.example.api.domain.Account;
import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.InquiryCommand;
import com.example.api.inquiry.dto.InquiryRequest;
import com.example.api.inquiry.dto.InquiryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    @Transactional
    public Inquiry saveInquiry(
            @Validated final InquiryRequest inquiryRequest,
            @Validated final Account account
    ) {
        final InquiryCommand command = inquiryRequest.toCommand(account);
        final Inquiry inquiry = mapToInquiry(command);
        return inquiryRepository.save(inquiry);
    }

    @Transactional(readOnly = true)
    public List<InquiryResponse> getInquiriesByAccountId(
            @Validated final Long accountId
    ) {
        final List<Inquiry> inquiries = inquiryRepository.findByCreatedByAccountId(accountId);
        return inquiries.stream()
                .map(this::mapToInquiryResponse)
                .collect(Collectors.toList());
    }

    private Inquiry mapToInquiry(@Validated final InquiryCommand command) {
        final Inquiry inquiry = new Inquiry();
        inquiry.setInquiryType(command.inquiryType());
        inquiry.setSubInquiryType(command.subInquiryType());
        inquiry.setTitle(command.title());
        inquiry.setContent(command.content());
        inquiry.setInquiryStatus(Inquiry.InquiryStatus.valueOf(command.inquiryStatus()));
        inquiry.setAnswerDate(command.answerDate());
        inquiry.setCreatedBy(command.createdBy());
        return inquiry;
    }

    private InquiryResponse mapToInquiryResponse(@Validated final Inquiry inquiry) {
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