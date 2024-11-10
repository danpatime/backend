package com.example.api.contract.service;

import com.example.api.board.repository.ContractRepository;
import com.example.api.contract.controller.domain.BusinessInfoDTO;
import com.example.api.contract.controller.domain.ContractDTO;
import com.example.api.contract.controller.domain.EmployeeInfoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractDTO getContractInfo(long contractId) {
        BusinessInfoDTO businessDTO = contractRepository.findBusinessDTOByContractId(contractId);
        EmployeeInfoDTO employeeDTO = contractRepository.findEmployeeDTOByContractId(contractId);
        return new ContractDTO(businessDTO, employeeDTO);
    }
}
