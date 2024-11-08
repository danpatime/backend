package com.example.api.board.repository;

import com.example.api.board.controller.domain.InnerCareerDTO;
import com.example.api.domain.OfferEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferEmploymentRepository extends JpaRepository<OfferEmployment, Long> {
    @Query("select new com.example.api.board.controller.domain.InnerCareerDTO(b.businessName, c.contractStartTime, b.representationName, r) from OfferEmployment o " +
            "join Contract c on o.suggestId = c.contractId " +
            "join Business b on o.business.businessId = b.businessId "+
            "join Review r on o.suggestId = r.suggestId " +
            "where o.employee.employeeId = :employeeId")
    List<InnerCareerDTO> findAllDTOByEmployeeId(@Param("employeeId") long employeeId);
}
