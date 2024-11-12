package com.example.api.contracts;

import com.example.api.contracts.dto.SuggestedBusiness;
import com.example.api.domain.OfferEmployment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfferRepository extends JpaRepository<OfferEmployment, Long> {
    @Query("SELECT new com.example.api.contracts.dto.SuggestedBusiness(offer.business.businessId, offer.suggestStartTime, offer.suggestEndTime, offer.suggestHourlyPay, offer.suggestReaded, offer.suggestSucceeded) "
            + "FROM OfferEmployment offer "
            + "WHERE offer.employee.employeeId = :employeeId")
    List<SuggestedBusiness> queryEmployersSuggests(@Param("employeeId") final Long employeeId);
}
