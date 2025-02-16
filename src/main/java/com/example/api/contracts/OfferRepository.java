package com.example.api.contracts;

import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.domain.OfferEmployment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfferRepository extends JpaRepository<OfferEmployment, Long> {
}