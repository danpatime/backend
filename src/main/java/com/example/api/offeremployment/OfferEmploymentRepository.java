package com.example.api.offeremployment;

import com.example.api.domain.OfferEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferEmploymentRepository extends JpaRepository<OfferEmployment, Long> {
}
