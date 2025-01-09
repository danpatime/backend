package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "BUSINESS_LOCATION")
    private String location;

    private String representationName;

    @Column(name = "BUSINESS_OPEN_DATE")
    private LocalDate openDate;

    @Column(name = "BUSINESS_REGISTRATION_NUMBER")
    private String registrationNumber;

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Business(String businessName, String location, String representationName) {
        this.businessName = businessName;
        this.location = location;
        this.representationName = representationName;
    }
}
