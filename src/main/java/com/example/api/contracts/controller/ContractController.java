package com.example.api.contracts.controller;

import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.*;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class ContractController {
    private final ContractService contractService;

    @PutMapping("/api/v1/contracts/{contractId}")
    public ResponseEntity<?> updateContractCondition(
            @PathVariable(required = true) final Long contractId,
            @RequestBody final UpdateContractConditionRequest updateContractConditionRequest
    ) {
        final UpdateContractConditionCommand updateCommand = updateContractConditionRequest.toCommand(contractId);
        contractService.updateContract(updateCommand);
        return ResponseEntity.ok(null);
    }

    record UpdateContractConditionRequest(
            LocalDateTime suggestStartDateTime,
            LocalDateTime suggestEndDateTime,
            Integer suggestHourlyPayment
    ) {
        UpdateContractConditionCommand toCommand(final Long contractId) {
            return new UpdateContractConditionCommand(contractId, this.suggestStartDateTime, this.suggestEndDateTime, this.suggestHourlyPayment);
        }
    }

    @PostMapping("/api/v1/contracts/suggests/{suggestId}/chatroom")
    public ResponseEntity<?> createChatRoom(
            @RequestBody final AcceptSuggestCommand acceptSuggestCommand
    ) {
        contractService.createChatRoom(acceptSuggestCommand);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/api/v1/contracts/{contractId}")
    public ResponseEntity<?> updateContractCondition(
            @PathVariable(required = true) final Long contractId,
            @RequestBody final UpdateContractConditionRequest updateContractConditionRequest
    ) {
        final UpdateContractConditionCommand updateCommand = updateContractConditionRequest.toCommand(contractId);
        contractService.updateContract(updateCommand);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/api/v1/contracts/{contractId}/accepts")
    public ResponseEntity<?> acceptContract(
            @PathVariable(required = true) final Long contractId
    ) {
        final AcceptContractCommand acceptContractCommand = new AcceptContractCommand(contractId);
        contractService.acceptContract(acceptContractCommand);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/api/v1/contract/{contractId}/status")
    public ResponseEntity<ContractDTO> getContractInfo(@PathVariable(required = true) final Long contractId) {
        final AcceptContractCommand contractStatusCommand = new AcceptContractCommand(contractId);
        ContractDTO contractDTO = contractService.getContractInfo(contractStatusCommand);
        return ResponseEntity.ok(contractDTO);
    }
}