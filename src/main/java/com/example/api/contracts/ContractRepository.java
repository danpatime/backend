package com.example.api.contracts;

import com.example.api.contracts.dto.BusinessInfoDTO;
import com.example.api.contracts.dto.EmployeeInfoDTO;
import com.example.api.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("select new com.example.api.contracts.dto." +
            "BusinessInfoDTO(b.businessName, b.representationName, c.contractStartTime, c.contractEndTime, b.location, e.phoneNumber, c.updatedDate) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.business b " +
            "join oe.employee e on e.accountId = b.employer.accountId " +
            "where c.contractId = :contractId")
    BusinessInfoDTO findBusinessDTOByContractId(@Param("contractId") long contractId);

    @Query("select new com.example.api.contracts.dto." +
            "EmployeeInfoDTO(e.name, e.phoneNumber, e.starPoint, e.workCount) " +
            "from Contract c " +
            "join c.offerEmployment oe " +
            "join oe.employee e " +
            "where c.contractId = :contractId")
    EmployeeInfoDTO findEmployeeDTOByContractId(@Param("contractId") long contractId);
}
