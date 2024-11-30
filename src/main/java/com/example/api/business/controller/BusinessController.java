package com.example.api.business.controller;

import com.example.api.business.BusinessService;
import com.example.api.business.dto.BusinessDetailsResponse;
import com.example.api.business.dto.QueryBusinessDetailCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class BusinessController {
    private final BusinessService businessService;


    @GetMapping("/api/v1/business/{businessId}")
    public ResponseEntity<BusinessDetailsResponse> getBusinessDetails(
            @PathVariable(required = true) final Long businessId
    ) {
        final QueryBusinessDetailCommand queryBusinessDetailCommand = new QueryBusinessDetailCommand(businessId);
        final BusinessDetailsResponse businessDetailsResponse = businessService.loadDetails(queryBusinessDetailCommand);
        return ResponseEntity.ok(businessDetailsResponse);
    }
}
