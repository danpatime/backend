package com.example.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "REVIEW")
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @OneToOne(mappedBy = "review")
    private Contract contract;
    @Column(name = "REVIEW_STAR_POINT")
    private int reviewStarPoint;
    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

    public Review(final int reviewStarPoint, final String reviewContent) {
        this.reviewStarPoint = reviewStarPoint;
        this.reviewContent = reviewContent;
    }
}

