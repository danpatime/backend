package com.example.api.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "OFFER_EMPLOYMENT")
public class OfferEmployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestId;

    private Long businessId;
    private Long workerId;
    private LocalDateTime suggestStartTime;
    private LocalDateTime suggestEndTime;
    private int suggestHourlyPay;
    private LocalDateTime suggestRegisterTime;
    private boolean suggestSucceeded;

    // getters and setters
}

