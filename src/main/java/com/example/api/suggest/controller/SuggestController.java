package com.example.api.suggest.controller;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import com.example.api.suggest.service.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SuggestController {
    private final SuggestService suggestService;

    @GetMapping("/api/v1/employment-suggests/status/{businessId}")
    public ResponseEntity<List<SuggestStatusDTO>> getBusinessSuggestStatus(
            @PathVariable Long businessId) {
        BusinessIdRequest businessIdRequest = new BusinessIdRequest(businessId);
        return ResponseEntity.ok(suggestService.getBusinessSuggestStatus(businessIdRequest));
    }

    @GetMapping("/api/v1/employment-suggests/employee/status")
    public ResponseEntity<List<SuggestStatusDTO>> getEmployeeSuggestStatus(
            @AuthenticationPrincipal final Long employeeId
    ) {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(employeeId);
        return ResponseEntity.ok(suggestService.getEmployeeSuggestStatus(employeeIdRequest));
    }

    @PostMapping("/api/v1/contracts/suggests/{suggestId}/accept")
    public ResponseEntity<String> acceptContractContact(
            @PathVariable(required = true) final Long suggestId
    ) {
        final AcceptSuggestCommand acceptSuggestCommand = new AcceptSuggestCommand(suggestId);
        suggestService.acceptSuggest(acceptSuggestCommand);
        return ResponseEntity.ok("성공적으로 제안을 수락하였습니다.");
    }

    @PostMapping("/api/v1/contracts/suggests/chatroom")
    public ResponseEntity<String> createChatRoom(
            @RequestBody final AcceptSuggestCommand acceptSuggestCommand
    ) {
        suggestService.createChatRoom(acceptSuggestCommand);
        return ResponseEntity.ok("성공적으로 채팅방을 생성하였습니다.");
    }
}
