package com.example.api.account.entity;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_UNIQUE_ID")
    private Long id;
    @Column(name = "ADDRESS_ZIPCODE")
    private String zipcode;
    @Column(name = "ADDRESS_")
    private String address;
    @Column(name = "ADDRESS_DETAIL_ADDRESS")
    private String detailAddress;
}
