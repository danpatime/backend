package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "FLAVORED_CATEGORY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"EMPLOYEE_ID", "SUB_CATEGORY_ID"})
})
public class FlavoredCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLAVORED_CATEGORY_ID")
    private Long flavoredCategoryId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SubCategory subCategory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employee;

    public FlavoredCategory(Category category, SubCategory subCategory, Account employee) {
        this.category = category;
        this.subCategory = subCategory;
        this.employee = employee;
    }
}