package com.example.api.inquiry;

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
    public InquiryResponse saveInquiry(
            @Validated final InquiryRequest inquiryRequest,
            @Validated final Long memberId
    ) {
        final InquiryCommand command = inquiryRequest.toCommand(memberId);
        final Inquiry inquiry = mapToInquiry(command);
        Inquiry savedInquiry = inquiryRepository.save(inquiry);
        return mapToInquiryResponse(savedInquiry);
    }

    @Transactional(readOnly = true)
    public List<InquiryResponse> getInquiriesByAccountId(@Validated final Long memberId) {
        final List<Inquiry> inquiries = inquiryRepository.findByCreatedBy(memberId);
        return inquiries.stream()
                .map(this::mapToInquiryResponse)
                .collect(Collectors.toList());
    }

    private Inquiry mapToInquiry(@Validated final InquiryCommand command) {
        return new Inquiry(
                command.createdBy(),
                command.inquiryType(),
                command.subInquiryType(),
                command.title(),
                command.content(),
                Inquiry.InquiryStatus.valueOf(command.inquiryStatus()),
                command.answerDate()
        );
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

