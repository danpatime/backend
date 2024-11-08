package com.example.api.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "EMPLOYEE_STAR_RATING")
    private float starPoint;
    @Column(name = "EMPLOYEE_WORK_COUNT")
    private int workCount;
    @Column(name = "EMPLOYEE_NICKNAME")
    private String nickname;
    @Column(name = "EMPLOYEE_OPEN_STATUS", columnDefinition = "boolean DEFAULT true")
    private Boolean openStatus;
}

