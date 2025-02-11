package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "SUB_CATEGORY")
public class SubCategory extends BaseEntity{
    @Id
    @Column(name = "SUB_CATEGORY_ID")
    private Long subCategoryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

        @Column(name = "SUB_CATEGORY_NAME")
    private String subCategoryName;
}