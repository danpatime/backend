package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "REVIEW")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestId;

    @Column(name = "REVIEW_STAR_POINT")
    private int reviewStarPoint;

    @Column(name = "REVIEW_CONTENT")
    private String reviewContent;

}

