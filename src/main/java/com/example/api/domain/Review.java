package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "REVIEW")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {
    @Id
    @Column(name ="REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_WRITER_ID")
    private Business writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKER_ID")
    private Account employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    private Contract contract;

    @Column(name = "REVIEW_STAR_POINT")
    private int reviewStarPoint;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;
    public Review(int reviewStarPoint, String reviewContent, Contract contract) {
        this.reviewStarPoint = reviewStarPoint;
        this.reviewContent = reviewContent;
        this.contract = contract;
    }
}


