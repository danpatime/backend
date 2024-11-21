package com.example.api.auth;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AuthRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);

}

