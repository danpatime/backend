package com.example.api.suggest.controller;

import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import com.example.api.suggest.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("/api/v1/employment-suggests/status/{businessId}")
    public ResponseEntity getSuggestStatus(@PathVariable() long businessId) {
        BusinessIdRequest businessIdRequest = new BusinessIdRequest(businessId);
        List<SuggestStatusDTO> suggestStatus = suggestService.getSuggestStatus(businessIdRequest);
        return ResponseEntity.ok(suggestStatus);
    }
    @GetMapping("/api/v1/contracts/employment-suggests")
    public ResponseEntity<List<SuggestedBusinessResponse>> getAllSuggest(
            @RequestParam(required = true) final Long employeeId
    ) {
        final QueryAllSuggestsForMeCommand queryAllSuggestsForMeCommand = new QueryAllSuggestsForMeCommand(employeeId);
        final List<SuggestedBusinessResponse> suggestedBusinesses = suggestService.getAllRelatedSuggests(
                queryAllSuggestsForMeCommand);
        return ResponseEntity.ok(suggestedBusinesses);
    }

    @PostMapping("/api/v1/contracts/suggests/{suggestId}/accept")
    public ResponseEntity<?> acceptContractContact(
            @PathVariable(required = true) final Long suggestId
    ) {
        final AcceptSuggestCommand acceptSuggestCommand = new AcceptSuggestCommand(suggestId);
        suggestService.acceptSuggest(acceptSuggestCommand);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/api/v1/contracts/suggests/{suggestId}/chatroom")
    public ResponseEntity<?> createChatRoom(
            @RequestBody final AcceptSuggestCommand acceptSuggestCommand
    ) {
        suggestService.createChatRoom(acceptSuggestCommand);
        return ResponseEntity.ok(null);
    }
}
