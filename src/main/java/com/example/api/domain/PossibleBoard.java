package com.example.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PossibleBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long possibleId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account employee;

    @Column(name = "POSSIBLE_START_TIME")
    private LocalDateTime startTime;

    @Column(name = "POSSIBLE_END_TIME")
    private LocalDateTime endTime;

    public PossibleBoard(Account employee, LocalDateTime startTime, LocalDateTime endTime) {
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

