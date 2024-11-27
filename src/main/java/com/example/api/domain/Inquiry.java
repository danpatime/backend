package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "INQUIRY")
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    private String inquiryType;
    private String subInquiryType;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;

    private String createdBy;

    private String processStatus;

    @Column(name = "answer_date")
    private String answerDate;

    public enum InquiryStatus {
        WAITING, COMPLETED
    }
}
