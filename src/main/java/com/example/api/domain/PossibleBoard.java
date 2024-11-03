package com.example.api.domain;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.FetchType.*;

@Entity
public class PossibleBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long possibleId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "POSSIBLE_START_TIME")
    private Date startTime;

    @Column(name = "POSSIBLE_END_TIME")
    private Date endTime;
}

