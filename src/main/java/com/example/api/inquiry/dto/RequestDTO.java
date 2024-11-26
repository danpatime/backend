package com.example.api.inquiry.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    private String inquiryType;
    private String subInquiryType;
    private String title;
    private String content;
    private String createdBy;
}
