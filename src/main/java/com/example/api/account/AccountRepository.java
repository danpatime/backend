package com.example.api.account;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends JpaRepository<Account, Long> {
}
