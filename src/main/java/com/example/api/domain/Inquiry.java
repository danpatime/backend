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
    @Column(name = "INQUIRY_ID")
    private Long inquiryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ACCOUNT_UNIQUE_ID")
    private Account createdBy;

    @Column(name = "INQUIRY_TYPE")
    private String inquiryType;

    @Column(name = "SUB_INQUIRY_TYPE")
    private String subInquiryType;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "INQUIRY_STATUS")
    private InquiryStatus inquiryStatus;

    @Column(name = "ANSWER_DATE")
    private LocalDateTime answerDate;
    public enum InquiryStatus {
        WAITING, COMPLETED
    }
}
