package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties({"createdDate", "updatedDate"})
@Table(name = "LOCATION")
public class Location extends BaseEntity {
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
    @Column(name = "LOCATION_SIDO")
    private String sido;
    @Column(name = "LOCATION_SIGUGUN")
    private String sigugun;
    @Column(name = "LOCATION_DONG")
    private String dong;

    public Location() {
    }

    public Location(String dong, String sigugun, String sido, String detailAddress, String address, String zipcode) {
        this.dong = dong;
        this.sigugun = sigugun;
        this.sido = sido;
        this.detailAddress = detailAddress;
        this.address = address;
        this.zipcode = zipcode;
    }
}