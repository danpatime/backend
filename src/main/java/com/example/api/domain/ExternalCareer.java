package com.example.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "EXTERNAL_CAREER")
public class ExternalCareer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private Account employee;

    @EqualsAndHashCode.Include
    @Column(name = "BUSINESS_NAME")
    private String Name;
    @EqualsAndHashCode.Include
    @Column(name = "PART_TIME_PERIOD")
    private String period;

    public ExternalCareer(Account employee, String name, String period) {
        this.employee = employee;
        Name = name;
        this.period = period;
    }

    public ExternalCareer() {
    }
}