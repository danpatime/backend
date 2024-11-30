package com.example.api.support;

import com.example.api.support.dto.SummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/support")
public class SupportController {
    private final SupportService supportService;

    @GetMapping
    public SummaryDTO getSupportSummary() {
        return supportService.getSupportSummary();
    }
}
