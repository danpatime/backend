package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "OFFER_EMPLOYMENT")
public class OfferEmployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_ID")
    private Business business;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "SUGGEST_START_TIME")
    private LocalDateTime suggestStartTime;
    @Column(name = "SUGGEST_END_TIME")
    private LocalDateTime suggestEndTime;
    @Column(name = "SUGGEST_HOURLY_PAY")
    private int suggestHourlyPay;
    @Column(name = "SUGGEST_SUCCEDED", columnDefinition = "boolean DEFAULT false")
    private boolean suggestSucceeded;
    @Column(name = "SUGGEST_REGISTER_TIME")
    private LocalDateTime suggestRegisterTime;

    @PrePersist
    protected void onCreate() {
        this.suggestRegisterTime = LocalDateTime.now();
    }

}

