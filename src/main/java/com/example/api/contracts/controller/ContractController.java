package com.example.api.contracts.controller;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.*;

import java.time.LocalDateTime;
import java.util.List;

import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class ContractController {
    private final ContractService contractService;

    @PutMapping("/api/v1/contracts/{contractId}")
    public ResponseEntity<String> updateContractCondition(
            @PathVariable(required = true) final Long contractId,
            @RequestBody final UpdateContractConditionRequest updateContractConditionRequest
    ) {
        final UpdateContractConditionCommand updateCommand = updateContractConditionRequest.toCommand(contractId);
        contractService.updateContract(updateCommand);
        return ResponseEntity.ok("근무 조건이 변경되었습니다.");
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

    @PostMapping("/api/v1/contracts/{contractId}/accepts")
    public ResponseEntity<String> acceptContract(
            @PathVariable(required = true) final Long contractId
    ) {
        final AcceptContractCommand acceptContractCommand = new AcceptContractCommand(contractId);
        contractService.acceptContract(acceptContractCommand);
        return ResponseEntity.ok("성공적으로 수락되었습니다.");
    }

    @GetMapping("/api/v1/contracts/{contractId}/status")
    public ResponseEntity<ContractDTO> getContractInfo(@PathVariable(required = true) final Long contractId) {
        final AcceptContractCommand contractStatusCommand = new AcceptContractCommand(contractId);
        ContractDTO contractDTO = contractService.getContractInfo(contractStatusCommand);
        return ResponseEntity.ok(contractDTO);
    }
}