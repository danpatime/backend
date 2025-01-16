package com.example.api.business.controller;

import com.example.api.account.entity.Location;
import com.example.api.business.BusinessQueryService;
import com.example.api.business.BusinessService;
import com.example.api.business.dto.AddBusinessCommand;
import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.business.dto.QueryBusinessDetailCommand;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/business")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final BusinessQueryService businessQueryService;

    @GetMapping
    public ResponseEntity<?> getMyBusiness(
            @RequestParam final Long businessId
    ) {
        return ResponseEntity.ok(businessQueryService.loadDetails(new QueryBusinessDetailCommand(businessId)));
    }

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
            Location location,
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
            Location location,
            String representationName,
            List<Long> categoryId
    ) {
        ModifyBusinessCommand toCommand() {
            return new ModifyBusinessCommand(businessId, businessName, location, representationName, categoryId);
        }
    }
}
