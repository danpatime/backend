package com.example.api.auth.entitiy;

import com.example.api.domain.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Long id;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "IS_EXPIRED")
    private boolean isExpired = false;

    @Column(name = "RECENT_LOGIN")
    private LocalDateTime recentLogin = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_UNIQUE_ID", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Account user;

    protected RefreshToken() {
    }

    public RefreshToken(Account user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void putRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void expire() {
        isExpired = true;
    }

    public boolean isExpired() {
        return isExpired;
    }
}