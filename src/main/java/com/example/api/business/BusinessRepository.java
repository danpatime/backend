package com.example.api.business;

import com.example.api.domain.Business;
import com.example.api.employer.controller.dto.EmployerBusinessesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    @Query("SELECT b FROM Business b JOIN FETCH b.employer JOIN FETCH b.businessCategories WHERE b.businessId = :businessId")
    Optional<Business> getDetails(@Param("businessId") final Long businessId);

    @Query("select new com.example.api.employer.controller.dto.EmployerBusinessesRequest(b.businessName, b.location) from Business b where b.employer.accountId = :employerId order by b.location.id")
    List<EmployerBusinessesRequest> findBusinessesByEmployerId(@Param("employerId")final Long employerId);
}