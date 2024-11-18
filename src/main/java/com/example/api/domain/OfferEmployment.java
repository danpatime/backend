package com.example.api.domain;

import com.example.api.offeremployment.dto.OfferEmploymentCommand;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode
@Table(name = "OFFER_EMPLOYMENT")
@NoArgsConstructor
public class OfferEmployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Business business;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;
    @OneToOne(mappedBy = "offerEmployment", cascade = CascadeType.ALL)
    private Contract contract;
    @Column(name = "SUGGEST_START_TIME")
    private LocalDateTime suggestStartTime;
    @Column(name = "SUGGEST_END_TIME")
    private LocalDateTime suggestEndTime;
    @Column(name = "SUGGEST_HOURLY_PAY")
    private int suggestHourlyPay;
    @Column(name = "SUGGEST_READED", columnDefinition = "boolean DEFAULT false")
    private boolean suggestReaded;
    @Column(name = "SUGGEST_SUCCEDED", columnDefinition = "boolean DEFAULT false")
    private boolean suggestSucceeded;
    @Column(name = "SUGGEST_REGISTER_TIME")
    private LocalDateTime suggestRegisterTime;

    @Column(name = "SUGGEST_FINISHED", columnDefinition = "boolean DEFAULT false")
    private boolean suggestFinished;

    public static OfferEmployment fromCommand(OfferEmploymentCommand offerEmploymentCommand, Account employee, Business business) {
        return new OfferEmployment(
                business,
                employee,
                offerEmploymentCommand.suggestStartTime(),
                offerEmploymentCommand.suggestEndTime(),
                offerEmploymentCommand.suggestHourlyPay()
        );
    }

    public OfferEmployment(Business business, Account employee, LocalDateTime suggestStartTime, LocalDateTime suggestEndTime, int suggestHourlyPay) {
        this.business = business;
        this.employee = employee;
        this.suggestStartTime = suggestStartTime;
        this.suggestEndTime = suggestEndTime;
        this.suggestHourlyPay = suggestHourlyPay;
    }

    @PrePersist
    protected void onCreate() {
        this.suggestRegisterTime = LocalDateTime.now();
    }

    public void succeeded() {
        this.suggestSucceeded = true;
    }
}
