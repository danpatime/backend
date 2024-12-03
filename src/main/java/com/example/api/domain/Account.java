package com.example.api.domain;

import com.example.api.account.domain.Nationality;
import com.example.api.account.domain.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AttributeOverride(name = "createdDate", column = @Column(name = "ACCOUNT_REGISTERED_DATETIME"))
@Table(name = "ACCOUNT")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_UNIQUE_ID")
    private Long accountId;

    @Column(name = "ACCOUNT_ID")
    private String loginId;
    @Column(name = "ACCOUNT_PASSWORD")
    private String password;
    @Column(name = "ACCOUNT_NAME")
    private String name;
    @Column(name = "ACCOUNT_NICKNAME")
    private String nickname;
    @Column(name = "ACCOUNT_PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "ACCOUNT_EMAIL")
    private String email;
    @Column(name = "ACCOUNT_NATIONALITY")
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    @Column(name = "ACCOUNT_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "ACCOUNT_SEX")
    private String sex;
    @Column(name = "ACCOUNT_AGE")
    private int age;
    @Column(name = "ACCOUNT_PROFILE_IMAGE")
    private String profileImage;
    @Column(name = "ACCOUNT_STAR_RATING")
    private float starPoint;
    @Column(name = "ACCOUNT_WORK_COUNT")
    private int workCount;
    @Column(name = "ACCOUNT_OPEN_STATUS")
    private boolean openStatus;
    @Column(name = "ACCOUNT_DELETED", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted = false;

    public Account() {
    }

    public Account(String loginId, String password, String name, String nickname, String phoneNumber, String email, Nationality nationality, UserRole role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.role = role;
        this.starPoint =  0.0f;
        this.workCount = 0;
        this.openStatus = true;
    }
}