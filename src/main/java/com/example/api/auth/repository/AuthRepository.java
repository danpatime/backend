package com.example.api.auth.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Account, Long> {
    Optional<Account> findUserByLoginId(String loginId);
}
