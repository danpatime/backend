package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "BUSINESS")
public class Business extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_EMPLOYER_ID")
    private Account employer;

    @OneToMany(mappedBy = "business")
    private List<BusinessCategory> businessCategories = new ArrayList<>();

    @Column(name = "BUSINESS_NAME")
    private String businessName;

    @Column(name = "BUSINESS_LOCATION")
    private String location;

    private String representationName;

    @Column(name = "BUSINESS_OPEN_DATE")
    private LocalDate openDate;

    @Column(name = "BUSINESS_REGISTRATION_NUMBER")
    private String registrationNumber;

}
