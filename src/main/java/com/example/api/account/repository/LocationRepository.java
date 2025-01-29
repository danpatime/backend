package com.example.api.account.repository;

import com.example.api.business.domain.BusinessLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<BusinessLocation, Long> {
}
