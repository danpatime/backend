package com.example.api.business.controller;

import com.example.api.business.BusinessService;
import com.example.api.business.dto.AddBusinessCommand;
import com.example.api.business.dto.ModifyBusinessCommand;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PutMapping
    public ResponseEntity<?> modifyMyBusiness(
            @RequestBody final ModifyBusinessRequest request
    ) {
        final ModifyBusinessCommand command = request.toCommand();
        businessService.updateBusiness(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addBusiness(
            @RequestBody final AddBusinessRequest request
    ) {
        final AddBusinessCommand command = request.toCommand();
        businessService.addBusiness(command);
        return ResponseEntity.ok().build();
    }

    record AddBusinessRequest(
            Long requestMemberId,
            String businessName,
            String location,
            List<Long> categoryIds,
            String representationName
    ) {
        AddBusinessCommand toCommand() {
            return new AddBusinessCommand(requestMemberId, businessName, location, categoryIds, representationName);
        }
    }

    record ModifyBusinessRequest(
            @NotNull
            Long businessId,
            String businessName,
            String location,
            String representationName,
            List<Long> categoryId
    ) {
        ModifyBusinessCommand toCommand() {
            return new ModifyBusinessCommand(businessId, businessName, location, representationName, categoryId);
        }
    }
}
