package com.example.api.inquiry;


import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public Inquiry saveInquiry(RequestDTO inquiryRequestDTO) {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryType(inquiryRequestDTO.getInquiryType());
        inquiry.setSubInquiryType(inquiryRequestDTO.getSubInquiryType());
        inquiry.setTitle(inquiryRequestDTO.getTitle());
        inquiry.setContent(inquiryRequestDTO.getContent());
        inquiry.setResponseStatus(0);
        inquiry.setCreatedBy(inquiryRequestDTO.getCreatedBy());
        inquiry.setProcessStatus("처리중");
        inquiry.setAnswerDate(null);

        return inquiryRepository.save(inquiry);
    }


    public List<Inquiry> getInquiriesByUser(String createdBy) {
        return inquiryRepository.findByCreatedBy(createdBy);
    }


    public Inquiry updateResponseStatus(Long inquiryId, int responseStatus, String processStatus) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("문의가 없습니다."));
        inquiry.setResponseStatus(responseStatus);
        inquiry.setProcessStatus(processStatus);
        inquiry.setAnswerDate(LocalDateTime.now().toString());
        return inquiryRepository.save(inquiry);
    }
}



