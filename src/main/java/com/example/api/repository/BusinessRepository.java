package com.example.api.repository;

import com.example.api.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    boolean existsByRegistrationNumber(String registrationNumber);
}
