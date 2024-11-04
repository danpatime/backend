package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "BUSINESS_CATEGORY")
public class BusinessCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_CATEGORY_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CATEGOREY_ID")
    private Category category;
}

