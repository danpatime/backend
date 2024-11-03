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
    private Long employeeId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "EMPLOYEE_STAR_RATING")
    private float starPoint;

    @Column(name = "EMPLOYEE_WORK_COUNT")
    private String workCount;
    @Column(name = "EMPLOYEE_NICKNAME")
    private String nickname;

}

