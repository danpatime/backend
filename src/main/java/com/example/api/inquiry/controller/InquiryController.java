package com.example.api.inquiry.controller;


import com.example.api.domain.Inquiry;
import com.example.api.inquiry.InquiryService;
import com.example.api.inquiry.dto.InquiryRequest;
import com.example.api.inquiry.dto.InquiryCommand;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support")
public class InquiryController {
    private final InquiryService inquiryService;
    private final ModelMapper modelMapper;


    @PostMapping("/inquiry")
    public ResponseEntity<InquiryCommand> createInquiry(@RequestBody InquiryRequest requestDTO) {
        Inquiry inquiry = inquiryService.saveInquiry(requestDTO);
        InquiryCommand responseDTO = mapToResponseDTO(inquiry);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/my-inquiries")
    public ResponseEntity<List<InquiryCommand>> getMyInquiries(@RequestParam String createdBy) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByUser(createdBy);
        List<InquiryCommand> responseDTOs = inquiries.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    private InquiryCommand mapToResponseDTO(Inquiry inquiry) {
        return modelMapper.map(inquiry, InquiryCommand.class);
    }
}
