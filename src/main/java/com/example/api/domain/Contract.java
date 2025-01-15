package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "CONTRACT")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Contract extends BaseEntity {

    @Id
    @Column(name ="CONTRACT_ID")
    private Long contractId;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "CONTRACT_ID", referencedColumnName = "SUGGEST_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OfferEmployment offerEmployment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;

    @Column(name = "CONTRACT_START_TIME")
    private LocalDateTime contractStartTime;

    @Column(name = "CONTRACT_END_TIME")
    private LocalDateTime contractEndTime;

    @Column(name = "CONTRACT_HOURLY_PAY")
    private int contractHourlyPay;

    @Column(name = "CONTRACT_SUCCEDED", columnDefinition = "boolean DEFAULT false")
    private boolean contractSucceeded;

    public Contract(
            final OfferEmployment offerEmployment,
            final Account employee,
            final LocalDateTime contractStartTime,
            final LocalDateTime contractEndTime,
            final int contractHourlyPay,
            final boolean contractSucceeded
    ) {
        this.offerEmployment = offerEmployment;
        this.employee = employee;
        this.contractStartTime = contractStartTime;
        this.contractEndTime = contractEndTime;
        this.contractHourlyPay = contractHourlyPay;
        this.contractSucceeded = contractSucceeded;
    }

    public void updateHourlyPayment(final Integer hourlyPay) {
        this.contractHourlyPay = hourlyPay;
    }

    public void updateStartDateTime(final LocalDateTime contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public void updateEndDateTime(final LocalDateTime contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public boolean isValidContractRangeTime() {
        return this.contractStartTime.isBefore(this.contractEndTime);
    }

    public void succeed() {
        this.contractSucceeded = true;
    }
}