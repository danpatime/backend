package com.example.api.auth.entitiy;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails{
    private final Long userId;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(final Long userId, final Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getUserId() {
        return userId;
    }
}