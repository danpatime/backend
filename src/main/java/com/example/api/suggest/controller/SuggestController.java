package com.example.api.suggest.controller;

import com.example.api.suggest.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("/api/v1/employment-suggests/status/{employeeId}")
    public ResponseEntity getSuggestStatus(@PathVariable("employeeId") long employeeId) {
        suggestService.getSuggestStatus(employeeId);
        return ResponseEntity.ok("good");
    }
}
