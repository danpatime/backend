package com.example.api.contracts.controller;

import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusiness;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ContractController {
    private final ContractService contractService;

    @GetMapping("/api/v1/contracts/employment-suggests")
    public ResponseEntity<List<SuggestedBusiness>> getAllSuggest(
            @RequestParam(required = true) final Long employeeId
    ) {
        final QueryAllSuggestsForMeCommand queryAllSuggestsForMeCommand = new QueryAllSuggestsForMeCommand(employeeId);
        final List<SuggestedBusiness> suggestedBusinesses = contractService.getAllRelatedSuggests(
                queryAllSuggestsForMeCommand);
        return ResponseEntity.ok(suggestedBusinesses);
    }

    @PostMapping("/api/v1/contracts/suggests/{suggestId}/accept")
    public ResponseEntity<?> acceptContractContact(
            @PathVariable(required = true) final Long suggestId
    ) {
        final AcceptSuggestCommand acceptSuggestCommand = new AcceptSuggestCommand(suggestId);
        contractService.acceptSuggest(acceptSuggestCommand);
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

    record UpdateContractConditionRequest(
            LocalDateTime suggestStartDateTime,
            LocalDateTime suggestEndDateTime,
            Integer suggestHourlyPayment
    ) {
        UpdateContractConditionCommand toCommand(final Long contractId) {
            return new UpdateContractConditionCommand(contractId, this.suggestStartDateTime, this.suggestEndDateTime, this.suggestHourlyPayment);
        }
    }
}
