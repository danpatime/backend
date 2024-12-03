package com.example.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PossibleBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long possibleId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Account employee;

    @Column(name = "POSSIBLE_START_TIME")
    @EqualsAndHashCode.Include
    private LocalDateTime startTime;

    @Column(name = "POSSIBLE_END_TIME")
    @EqualsAndHashCode.Include
    private LocalDateTime endTime;

    public PossibleBoard(Account employee, LocalDateTime startTime, LocalDateTime endTime) {
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

