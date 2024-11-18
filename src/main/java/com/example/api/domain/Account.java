package com.example.api.domain;

import com.example.api.account.entity.Nationality;
import com.example.api.account.entity.UserRole;
import com.example.api.auth.dto.LoginUserRequest;
import com.example.api.board.dto.update.UpdateOpenStatusRequest;
import com.example.api.board.dto.update.UpdateUserInfoRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Collection;

@Entity
@Getter
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
    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "AUTHORITY", joinColumns = @JoinColumn(name = "ACCOUNT_UNIQUE_ID"), foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Enumerated(EnumType.STRING)
    private Collection<UserRole> roles;
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
    @Column(name = "ACCOUNT_EMAIL_RECEIVABLE", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean emailReceivable = false;

    public Account() {
    }

    public Account(String name, String email, Collection<UserRole> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public Account(String loginId, String password, String name, String nickname, String phoneNumber, String email, Nationality nationality, Collection<UserRole> roles, final Boolean emailReceivable) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.roles = roles;
        this.starPoint =  0.0f;
        this.workCount = 0;
        this.openStatus = true;
        this.emailReceivable = emailReceivable;
    }

    public Account(String loginId, String password, String email, String phoneNumber, Nationality nationality, Collection<UserRole> roles) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.roles = roles;
    }

    public LoginUserRequest getLoginUser(){
        return new LoginUserRequest(accountId);
    }

    public void updateOpenStatus(UpdateOpenStatusRequest request){
        this.openStatus = request.openStatus();
    }

    public void updateUserInfo(UpdateUserInfoRequest request){
        this.name = request.name();
        this.sex = request.sex();
        this.age = request.age();
        this.phoneNumber = request.phoneNumber();
        this.email = request.email();
        this.nickname = request.nickname();
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }
}