package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
@Table(name = "BUSINESS")
@NoArgsConstructor
public class Business extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_ID")
    private Long businessId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_EMPLOYER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employer;

    @OneToMany(mappedBy = "business")
    private List<BusinessCategory> businessCategories = new ArrayList<>();

    @Column(name = "BUSINESS_NAME")
    private String businessName;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "BUSINESS_LOCATION", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Location location;

    @Column(name = "REPRESENTATION_NAME")
    private String representationName;

    @Column(name = "BUSINESS_OPEN_DATE")
    private String openDate;

    @Column(name = "BUSINESS_REGISTRATION_NUMBER")
    private String registrationNumber;

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRepresentationName(String representationName) {
        this.representationName = representationName;
    }

    public Business(String businessName, Location location, String representationName) {
        this.businessName = businessName;
        this.location = location;
        this.representationName = representationName;
    }

    public Business(String businessName, Location location, String representationName, Account employer, String openDate, String registrationNumber) {
        this.businessName = businessName;
        this.location = location;
        this.representationName = representationName;
        this.employer = employer;
        this.openDate = openDate;
        this.registrationNumber = registrationNumber;
    }

    public Business(Account employer, String businessName, Location location) {
        this.employer = employer;
        this.businessName = businessName;
        this.location = location;
    }
}