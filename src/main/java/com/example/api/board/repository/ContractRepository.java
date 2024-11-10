package com.example.api.board.repository;

import com.example.api.contract.controller.domain.BusinessInfoDTO;
import com.example.api.contract.controller.domain.ContractDTO;
import com.example.api.contract.controller.domain.EmployeeInfoDTO;
import com.example.api.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select new com.example.api.contract.controller.domain." +
            "BusinessInfoDTO(b.businessName, b.representationName, c.contractStartTime, c.contractEndTime, b.location, a.phoneNumber, c.updatedDate) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.business b " +
            "join Account a on a.id = b.employer.employerId " +
            "where c.contractId = :contractId")
    BusinessInfoDTO findBusinessDTOByContractId(@Param("contractId") long contractId);

    @Query("select new com.example.api.contract.controller.domain." +
            "EmployeeInfoDTO(a.name, a.phoneNumber, e.starPoint, e.workCount) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.employee e " +
            "join e.account a " +
            "where c.contractId = :contractId")
    EmployeeInfoDTO findEmployeeDTOByContractId(@Param("contractId") long contractId);
}
