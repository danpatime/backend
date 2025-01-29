package com.example.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "CATEGPRY_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @EqualsAndHashCode.Include
    private Category category;

    private int workCount;

    public ExternalCareer(Account employee, Category category, int workCount) {
        this.employee = employee;
        this.category = category;
        this.workCount = workCount;
    }
}