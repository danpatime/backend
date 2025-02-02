package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "SCRAP")
public class Scrap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCRAP_ID")
    private Long scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employee;

    public Scrap() {
    }

    public Scrap(Account employee, Account employer) {
        this.employee = employee;
        this.employer = employer;
    }
}