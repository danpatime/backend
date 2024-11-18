package com.example.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "EXTERANL_CARRER")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExternalCareer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
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

