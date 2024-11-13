package com.example.api.contracts.controller;

import com.example.api.contracts.ContractService;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusiness;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
