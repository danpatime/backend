package com.example.api.support.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SummaryDTO {
    private final String inquiryUrl;
    private final String myInquiriesUrl;
    private final String announcementsUrl;
}
