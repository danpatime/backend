package com.example.api.inquiry;


import com.example.api.domain.Inquiry;
import com.example.api.inquiry.dto.RequestDTO;
import com.example.api.inquiry.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support")
public class InquiryController {
    private final InquiryService inquiryService;


    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDTO> createInquiry(@RequestBody RequestDTO RequestDTO) {
        Inquiry inquiry = inquiryService.saveInquiry(RequestDTO);
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


    @PutMapping("/inquiry/{inquiryId}/response")
    public ResponseEntity<ResponseDTO> updateResponseStatus(
            @PathVariable Long inquiryId,
            @RequestParam int responseStatus,
            @RequestParam String processStatus) {

        Inquiry inquiry = inquiryService.updateResponseStatus(inquiryId, responseStatus, processStatus);
        ResponseDTO responseDTO = mapToResponseDTO(inquiry);
        return ResponseEntity.ok(responseDTO);
    }

    private ResponseDTO mapToResponseDTO(Inquiry inquiry) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setInquiryId(inquiry.getInquiryId());
        responseDTO.setInquiryType(inquiry.getInquiryType());
        responseDTO.setSubInquiryType(inquiry.getSubInquiryType());
        responseDTO.setTitle(inquiry.getTitle());
        responseDTO.setContent(inquiry.getContent());
        responseDTO.setResponseStatus(inquiry.getResponseStatus());
        responseDTO.setProcessStatus(inquiry.getProcessStatus());
        responseDTO.setAnswerDate(inquiry.getAnswerDate());
        responseDTO.setCreatedBy(inquiry.getCreatedBy());
        return responseDTO;
    }
}
