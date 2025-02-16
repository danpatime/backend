package com.example.api.domain.repository;

import com.example.api.board.dto.response.InternalCareerResponse;
import com.example.api.domain.OfferEmployment;
import com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferEmploymentRepository extends JpaRepository<OfferEmployment, Long> {
    @Query("select new com.example.api.board.dto.response.InternalCareerResponse(o.suggestId, b.businessName, c.contractStartTime, c.contractEndTime) " +
            "from OfferEmployment o " +
            "join Contract c on o.suggestId = c.contractId " +
            "join Business b on o.business.businessId = b.businessId " +
            "where o.employee.accountId = :employeeId")
    List<InternalCareerResponse> findAllInternalCareerResponseByEmployeeId(@Param("employeeId") long employeeId);

    @Query("select o from OfferEmployment o where o.business.businessId = :businessId")
    List<OfferEmployment> findAllByBusinessBusinessId(Long businessId);

    @Query("select o from OfferEmployment o where o.employee.accountId = :employeeId")
    List<OfferEmployment> findAllByEmployeeId(Long employeeId);

    @Query("select new com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest(e.name, b.businessName, oe.status, oe.suggestHourlyPay, oe.suggestStartTime, oe.suggestEndTime, null) " +
            "from OfferEmployment oe " +
            "join oe.employee e " +
            "join oe.business b " +
            "where oe.suggestId = :offerEmploymentId")
    OfferEmploymentDetailRequest findSuggestByOfferEmploymentId(@Param("offerEmploymentId") Long offerEmploymentId);
}