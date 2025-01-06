package com.example.api.account.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<Account> findByEmail(String email);

    Optional<Account> findUserByLoginId(String loginId);
}