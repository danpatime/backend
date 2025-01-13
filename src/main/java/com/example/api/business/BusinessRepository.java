package com.example.api.business;

import com.example.api.domain.Business;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT b FROM Business b JOIN FETCH b.employer JOIN FETCH b.businessCategories WHERE b.businessId = :businessId")
    Optional<Business> getDetails(@Param("businessId") final Long businessId);

}
