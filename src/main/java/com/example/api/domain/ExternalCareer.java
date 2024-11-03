package com.example.api.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "EXTERANL_CARRER")
public class ExternalCareer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "BUSINESS_NAME")
    private String Name;
    @Column(name = "PART_TIME_PERIOD")
    private String period;
}

