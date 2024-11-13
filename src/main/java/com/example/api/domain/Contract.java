package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CONTRACT")
@NoArgsConstructor
public class Contract extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUGGEST_ID")
    private OfferEmployment offerEmployment;
    @Column(name = "CONTRACT_START_TIME")
    private LocalDateTime contractStartTime;
    @Column(name = "CONTRACT_END_TIME")
    private LocalDateTime contractEndTime;
    @Column(name = "CONTRACT_HOURLY_PAY")
    private int contractHourlyPay;
    @Column(name = "CONTRACT_SUCCEDED", columnDefinition = "boolean DEFAULT false")
    private boolean contractSucceeded;
<<<<<<< HEAD
=======

    public Contract(
            final OfferEmployment offerEmployment,
            final LocalDateTime contractStartTime,
            final LocalDateTime contractEndTime,
            final int contractHourlyPay,
            final boolean contractSucceeded
    ) {
        this.offerEmployment = offerEmployment;
        this.contractStartTime = contractStartTime;
        this.contractEndTime = contractEndTime;
        this.contractHourlyPay = contractHourlyPay;
        this.contractSucceeded = contractSucceeded;
    }
>>>>>>> 9fd56a2 (#6 feat(ContractService): 알바생 제안 수락 기능 구현)
}

