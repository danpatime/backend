package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "BUSINESS_CATEGORY")
@NoArgsConstructor
public class BusinessCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_CATEGORY_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Business business;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SubCategory subCategory;

    public BusinessCategory(Business business, SubCategory subCategory) {
        this.business = business;
        this.subCategory = subCategory;
    }
}