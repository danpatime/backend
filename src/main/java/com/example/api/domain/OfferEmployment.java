package com.example.api.domain;

import com.example.api.account.entity.Nationality;
import com.example.api.offeremployment.dto.OfferEmploymentCommand;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "OFFER_EMPLOYMENT")
@NoArgsConstructor
public class OfferEmployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUGGEST_ID")
    private Long suggestId;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Business business;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;
    @OneToOne(mappedBy = "offerEmployment", cascade = CascadeType.ALL)
    private Contract contract;
    @Column(name = "SUGGEST_START_TIME", columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime suggestStartTime;
    @Column(name = "SUGGEST_END_TIME", columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime suggestEndTime;
    @Column(name = "SUGGEST_HOURLY_PAY")
    private int suggestHourlyPay;
    @Column(name = "SUGGEST_STATUS")
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;
    @Column(name = "SUGGEST_REGISTER_TIME")
    private LocalDateTime suggestRegisterTime;

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
        this.status = ProposalStatus.PENDING;
    }

    @PrePersist
    protected void onCreate() {
        this.suggestRegisterTime = LocalDateTime.now();
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }
}