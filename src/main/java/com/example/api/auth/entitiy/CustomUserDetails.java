package com.example.api.auth.entitiy;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {
    private final Long userId;
    private final String name;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(final Long userId, final String name, final String email, final Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes(){
        return Map.of(
                "userId", userId,
                "name", name,
                "email", email
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return null;
    }
}