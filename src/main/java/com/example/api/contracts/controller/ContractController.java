package com.example.api.contracts.controller;

import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.AcceptContractCommand;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.AddContractReviewCommand;
import com.example.api.contracts.dto.AddContractReviewRequest;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.contracts.dto.UpdateContractConditionRequest;
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
    public ResponseEntity<List<SuggestedBusinessResponse>> getAllSuggest(
            @RequestParam(required = true) final Long employeeId
    ) {
        final QueryAllSuggestsForMeCommand queryAllSuggestsForMeCommand = new QueryAllSuggestsForMeCommand(employeeId);
        final List<SuggestedBusinessResponse> suggestedBusinesses = contractService.getAllRelatedSuggests(
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

    @PostMapping("/api/v1/contracts/{contractId}/accepts")
    public ResponseEntity<?> acceptContract(
            @PathVariable(required = true) final Long contractId
    ) {
        final AcceptContractCommand acceptContractCommand = new AcceptContractCommand(contractId);
        contractService.acceptContract(acceptContractCommand);
        return ResponseEntity.ok(null);
    }
}
