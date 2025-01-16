package com.example.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEW_REPORT")
public class ReviewReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;

    @Column(name = "REASON")
    private String reason;
}
