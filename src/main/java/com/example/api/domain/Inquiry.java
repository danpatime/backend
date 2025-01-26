package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "INQUIRY")
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    private Long createdBy;

    private String inquiryType;

    private String subInquiryType;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;

    private LocalDateTime answerDate;
    public enum InquiryStatus {
        WAITING, COMPLETED
    }

    public Inquiry() {}

    public Inquiry(Long createdBy, String inquiryType, String subInquiryType, String title, String content, InquiryStatus inquiryStatus, LocalDateTime answerDate) {
        this.createdBy = createdBy;
        this.inquiryType = inquiryType;
        this.subInquiryType = subInquiryType;
        this.title = title;
        this.content = content;
        this.inquiryStatus = inquiryStatus;
        this.answerDate = answerDate;
    }
}
