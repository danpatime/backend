package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "CITY_DISTRICT")
public class CityDistrict extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_DISTRICT_ID")
    private Long id;

    private String district;

    public CityDistrict(String district) {
        this.district = district;
    }
}