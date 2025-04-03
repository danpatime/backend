package com.example.api.contracts;

import com.example.api.domain.OfferEmployment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferEmployment, Long> {
}