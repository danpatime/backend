package com.example.api.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_UNIQUE_ID")
    private Long id;
    @Column(name = "LOCATION_ZIPCODE")
    private String zipcode;
    @Column(name = "LOCATION_ADDRESS")
    private String address;
    @Column(name = "LOCATION_DETAIL_ADDRESS")
    private String detailAddress;

    public Location() {
    }

    public Location(String zipcode, String address, String detailAddress) {
        this.zipcode = zipcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }
}