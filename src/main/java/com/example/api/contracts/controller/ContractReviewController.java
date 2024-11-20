package com.example.api.contracts.controller;

import com.example.api.contracts.ContractReviewService;
import com.example.api.contracts.dto.AddContractReviewCommand;
import com.example.api.contracts.dto.AddContractReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ContractReviewController {
    private final ContractReviewService contractReviewService;

    @PostMapping("/api/v1/contracts/{contractId}/reviews")
    public ResponseEntity<?> addContractReview(
            @PathVariable(required = true) final Long contractId,
            @RequestBody final AddContractReviewRequest addContractReviewRequest
    ) {
        final AddContractReviewCommand addReviewCommand = addContractReviewRequest.toCommand(contractId);
        contractReviewService.addContractReview(addReviewCommand);
        return ResponseEntity.ok(null);
    }
}
