package com.example.api.contracts.controller;

import com.example.api.contracts.dto.ContractDTO;
import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.AcceptContractCommand;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.contracts.dto.UpdateContractConditionRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody final AcceptSuggestCommand acceptSuggestCommand
    ) {
        contractService.acceptSuggest(acceptSuggestCommand);
        return ResponseEntity.ok(null);
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