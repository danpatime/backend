package com.example.api.auth.repository;

import com.example.api.auth.entitiy.RefreshToken;
import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(Account user);

    void deleteAllByUser(Account user);
}
