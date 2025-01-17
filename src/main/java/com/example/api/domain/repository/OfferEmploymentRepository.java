package com.example.api.domain.repository;

import com.example.api.board.dto.response.InnerCareerDTO;
import com.example.api.domain.OfferEmployment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferEmploymentRepository extends JpaRepository<OfferEmployment, Long> {
    @Query("select new com.example.api.board.dto.response.InnerCareerDTO(b.businessName, c.contractStartTime, b.representationName, r) " +
            "from OfferEmployment o " +
            "join Contract c on o.suggestId = c.contractId " +
            "join Business b on o.business.businessId = b.businessId "+
            "join Review r on o.suggestId = r.reviewId " +
            "where o.employee.accountId = :employeeId")
    List<InnerCareerDTO> findAllDTOByEmployeeId(@Param("employeeId") long employeeId);

    List<OfferEmployment> findAllByBusinessBusinessId(long businessId);

    @Query("select e.name, b.businessName, c.contractStartTime, c.contractEndTime " +
            "from OfferEmployment oe " +
            "join oe.contract c " +
            "join oe.employee e " +
            "join oe.business b " +
            "where oe.suggestId = :OfferEmploymentId")
    List<Object[]> findSuggestByOfferEmploymentId(@Param("OfferEmploymentId")long OfferEmploymentId);

    @Modifying
    @Query("update OfferEmployment oe " +
            "set oe.suggestFinished = true, oe.suggestEndTime = CURRENT_TIMESTAMP " +
            "where oe.suggestFinished = :suggestId")
    void updateSuggestStatusToFinishedBySuggestId(@Param("suggestId") Long suggestId);
}