package com.example.api.domain;

import com.example.api.account.entity.Location;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private Long businessId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_EMPLOYER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account employer;

    @OneToMany(mappedBy = "business")
    private List<BusinessCategory> businessCategories = new ArrayList<>();

    @Column(name = "BUSINESS_NAME")
    private String businessName;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "BUSINESS_LOCATION")
    private Location location;

    private String representationName;

    @Column(name = "BUSINESS_OPEN_DATE")
    private LocalDate openDate;

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

    public Business(Account user, String businessRegistrationNumber, String businessName, String representationName, String businessOpenDate, Location location) {
        this.employer = user;
        this.registrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.representationName = representationName;
        this.openDate = LocalDate.parse(businessOpenDate);
        this.location = location;
    }

    public Business(String businessName, Location location, String representationName, Account employer, LocalDate openDate, String registrationNumber) {
        this.businessName = businessName;
        this.location = location;
        this.representationName = representationName;
        this.employer = employer;
        this.openDate = openDate;
        this.registrationNumber = registrationNumber;
    }
}




