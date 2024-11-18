package com.example.api.suggest.controller;

import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import com.example.api.suggest.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("/api/v1/employment-suggests/status/{businessId}")
    public ResponseEntity getSuggestStatus(@PathVariable() long businessId) {
        BusinessIdRequest businessIdRequest = new BusinessIdRequest(businessId);
        List<SuggestStatusDTO> suggestStatus = suggestService.getSuggestStatus(businessIdRequest);
        return ResponseEntity.ok(suggestStatus);
    }
}
