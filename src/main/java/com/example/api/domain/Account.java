package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNT")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_UNIQUE_ID")
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private String loginId;
    @Column(name = "ACCOUNT_PASSWORD")
    private String password;
    @Column(name = "ACCOUNT_NAME")
    private String name;
    @Column(name = "ACCOUNT_SEX")
    private String sex;
    @Column(name = "ACCOUNT_AGE")
    private int age;
    @Column(name = "ACCOUNT_PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "ACCOUNT_REGISTERED_DATETIME")
    private Date registeredDatetime;
    @Column(name = "ACCOUNT_PROFILE_IMAGE")
    private String profileImage;
    @Column(name = "ACCOUNT_EMAIL")
    private String email;
    @Column(name = "ACCOUNT_DELETED", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted = false;
}
