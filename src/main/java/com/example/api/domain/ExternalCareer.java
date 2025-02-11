package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "EXTERNAL_CAREER")
public class ExternalCareer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @EqualsAndHashCode.Include
    @JsonIgnore
    private Account employee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @EqualsAndHashCode.Include
    private SubCategory subCategory;

    @Column(name = "WORK_COUNT")
    private int workCount;

    public ExternalCareer(Account employee, SubCategory subCategory, int workCount) {
        this.employee = employee;
        this.subCategory = subCategory;
        this.workCount = workCount;
    }
}