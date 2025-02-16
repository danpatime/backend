package com.example.api.contracts;

import com.example.api.board.dto.request.ContractDetailRequest;
import com.example.api.contracts.dto.BusinessInfoDTO;
import com.example.api.contracts.dto.ContractScheduleResponse;
import com.example.api.contracts.dto.EmployeeInfoDTO;
import com.example.api.domain.Contract;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.api.domain.PossibleBoard;
import com.example.api.review.dto.ReviewAvailableResponse;
import com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select new com.example.api.contracts.dto." +
            "BusinessInfoDTO(b.businessName, b.representationName, c.contractStartTime, c.contractEndTime, b.location, e.phoneNumber, c.updatedDate) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.business b " +
            "join oe.employee e " +
            "where c.contractId = :contractId")
    BusinessInfoDTO findBusinessDTOByContractId(@Param("contractId") Long contractId);

    @Query("select new com.example.api.contracts.dto." +
            "EmployeeInfoDTO(e.name, e.phoneNumber, e.starPoint, e.workCount) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.employee e " +
            "where c.contractId = :contractId")
    EmployeeInfoDTO findEmployeeDTOByContractId(@Param("contractId") long contractId);

    @Query("SELECT c FROM Contract c JOIN FETCH c.offerEmployment JOIN FETCH c.offerEmployment.business.employer WHERE c.contractId = :contractId")
    Optional<Contract> loadContractWithOfferEmployment(@Param("contractId") final Long contractId);

    @Query("select new com.example.api.review.dto." +
            "ReviewAvailableResponse(e.accountId, e.name) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.employee e " +
            "join oe.business b " +
            "where b.businessId = :businessId and oe.status = 'TERMINATED'")
    List<ReviewAvailableResponse> findAvailableReviewsByBusinessId(@Param("businessId") Long businessId);

    @Query("select new com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest(e.name, b.businessName, oe.status, c.contractHourlyPay, c.contractStartTime, c.contractEndTime, cr.chatRoomId) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join ChatRoom cr on cr.offerEmployment.suggestId = oe.suggestId " +
            "join oe.employee e " +
            "join oe.business b " +
            "where c.contractId = :contractId")
    OfferEmploymentDetailRequest findContractByContractId(@Param("contractId") Long contractId);

    @Query("select new com.example.api.board.dto.request.ContractDetailRequest(c.contractId, b.businessName, c.contractStartTime, c.contractEndTime) " +
            "from Contract c " +
            "Join OfferEmployment oe on c.contractId = oe.suggestId " +
            "Join oe.business b on oe.business.businessId = b.businessId " +
            "where oe.employee.accountId = :employeeId and c.contractStartTime >= :currentMonth")
    List<ContractDetailRequest> findScheduleFromCurrentMonth(@Param("employeeId")Long employeeId, @Param("currentMonth") LocalDateTime currentMonth);
}
