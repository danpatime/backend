package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Table(name = "INQUIRY")
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ACCOUNT_UNIQUE_ID")
    private Account createdBy;

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
}
