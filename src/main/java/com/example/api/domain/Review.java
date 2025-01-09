package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "REVIEW")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column(name ="REVIEW_ID")
    private Long reviewId;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "REVIEW_ID", referencedColumnName = "SUGGEST_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OfferEmployment offerEmployment;

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

