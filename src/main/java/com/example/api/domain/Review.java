package com.example.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "REVIEW")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestId;

    @Column(name = "REVIEW_STAR_POINT")
    private int reviewStarPoint;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

    @OneToOne(fetch = FetchType.LAZY)
    private Contract contract;

    public Review(int reviewStarPoint, String reviewContent, Contract contract) {
        this.reviewStarPoint = reviewStarPoint;
        this.reviewContent = reviewContent;
        this.contract = contract;
    }
}

