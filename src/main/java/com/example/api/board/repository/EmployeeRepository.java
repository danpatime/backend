package com.example.api.board.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(@Param("employeeId") Long employeeId);
}
