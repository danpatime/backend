package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "flavored", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "category_id"})
})
public class Flavored extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLAVORED_ID")
    private Long flavoredId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employee;

    public Flavored() {
    }

    public Flavored(Category category, Account employee) {
        this.category = category;
        this.employee = employee;
    }
}