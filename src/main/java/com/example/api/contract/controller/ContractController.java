package com.example.api.contract.controller;

import com.example.api.contract.controller.domain.ContractDTO;
import com.example.api.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/api/v1/contract/{contractId}/status")
    public ResponseEntity<ContractDTO> getContractInfo(@PathVariable("contractId") long contractId) {
        ContractDTO contractDTO = contractService.getContractInfo(contractId);
        return ResponseEntity.ok(contractDTO);
    }
}
