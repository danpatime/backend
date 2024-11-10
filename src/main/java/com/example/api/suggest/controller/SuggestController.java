package com.example.api.suggest.controller;

import com.example.api.suggest.controller.domain.SuggestStatusDTO;
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
    public ResponseEntity getSuggestStatus(@PathVariable("businessId") long businessId) {
        List<SuggestStatusDTO> suggestStatus = suggestService.getSuggestStatus(businessId);
        return ResponseEntity.ok(suggestStatus);
    }
}
