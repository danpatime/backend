package com.example.api.inquiry;

import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;

    public Inquiry saveInquiry(RequestDTO inquiryRequestDTO) {
        Inquiry inquiry = modelMapper.map(inquiryRequestDTO, Inquiry.class);
        inquiry.setInquiryStatus(Inquiry.InquiryStatus.WAITING);
        inquiry.setProcessStatus("처리중");
        inquiry.setAnswerDate(null);

        return inquiryRepository.save(inquiry);
    }


    public List<Inquiry> getInquiriesByUser(String createdBy) {
        return inquiryRepository.findByCreatedBy(createdBy);
    }

}



