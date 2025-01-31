package com.example.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "POSSIBLE_BOARD")
public class PossibleBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSSIBLE_ID")
    private Long possibleId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employee;

    @Column(name = "POSSIBLE_START_TIME", columnDefinition = "TIMESTAMP(0)")
    @EqualsAndHashCode.Include
    private LocalDateTime startTime;

    @Column(name = "POSSIBLE_END_TIME", columnDefinition = "TIMESTAMP(0)")
    @EqualsAndHashCode.Include
    private LocalDateTime endTime;

    public PossibleBoard(final Account employee, final LocalDateTime startTime, final LocalDateTime endTime) {
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

