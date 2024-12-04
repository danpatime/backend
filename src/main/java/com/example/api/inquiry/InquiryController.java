package com.example.api.inquiry;


import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.RequestDTO;
import com.example.api.inquiry.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> createInquiry(@RequestBody RequestDTO requestDTO) {
        Inquiry inquiry = inquiryService.saveInquiry(requestDTO);
        ResponseDTO responseDTO = mapToResponseDTO(inquiry);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/my-inquiries")
    public ResponseEntity<List<ResponseDTO>> getMyInquiries(@RequestParam String createdBy) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByUser(createdBy);
        List<ResponseDTO> responseDTOs = inquiries.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    private ResponseDTO mapToResponseDTO(Inquiry inquiry) {
        return modelMapper.map(inquiry, ResponseDTO.class);
    }
}
