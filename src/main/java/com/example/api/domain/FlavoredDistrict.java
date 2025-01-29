package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "FLAVORED_DISTRICT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"EMPLOYEE_ID", "CITY_DISTRICT_ID"})
})
public class FlavoredDistrict extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLAVORED_DISTRICT_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CITY_DISTRICT_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CityDistrict district;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employee;

    public FlavoredDistrict(CityDistrict district, Account employee) {
        this.district = district;
        this.employee = employee;
    }
}