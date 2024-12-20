package com.example.api.auth.entitiy;

import com.example.api.domain.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenId")
    private Long id;

    @Column
    private String refreshToken;

    @Column
    private boolean isExpired = false;

    @Column
    private LocalDateTime recentLogin = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_UNIQUE_ID")
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