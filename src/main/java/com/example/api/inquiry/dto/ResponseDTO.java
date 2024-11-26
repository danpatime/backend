package com.example.api.inquiry.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private Long inquiryId;
    private String inquiryType;
    private String subInquiryType;
    private String title;
    private String content;
    private int responseStatus;
    private String processStatus;
    private String answerDate;
    private String createdBy;
}
