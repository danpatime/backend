package com.example.api.business.domain;

import com.example.api.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@ToString
public class BusinessLocation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_UNIQUE_ID")
    @JsonIgnore
    private Long id;
    @Column(name = "LOCATION_ZIPCODE")
    private String zipcode;
    @Column(name = "LOCATION_ADDRESS")
    private String address;
    @Column(name = "LOCATION_DETAIL_ADDRESS")
    private String detailAddress;

    public BusinessLocation() {
    }

    public BusinessLocation(String zipcode, String address, String detailAddress) {
        this.zipcode = zipcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessLocation location = (BusinessLocation) o;
        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}