package com.example.api.business;

import com.example.api.business.dto.ModifyBusinessCommand;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PutMapping("/api/v1/business")
    public ResponseEntity<?> modifyMyBusiness(
            @RequestBody final ModifyBusinessRequest request
    ) {
        final ModifyBusinessCommand command = request.toCommand();
        businessService.updateBusiness(command);
        return ResponseEntity.ok().build();
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
