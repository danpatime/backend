package com.example.api.account.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);
}
