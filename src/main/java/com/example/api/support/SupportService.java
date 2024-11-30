package com.example.api.support;

import com.example.api.support.dto.SummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportService {

    public SummaryDTO getSupportSummary() {
        return  new SummaryDTO(
                "/api/v1/support/inquiry",
                "/api/v1/support/my-inquiries",
                "/api/v1/support/announcements"
                );
    }
}
