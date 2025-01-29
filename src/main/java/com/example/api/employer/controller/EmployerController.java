package com.example.api.employer.controller;

import com.example.api.employer.controller.dto.EmployerBusinessesRequest;
import com.example.api.employer.controller.dto.EmployerIdRequest;
import com.example.api.employer.controller.dto.LikeEmployeeDTO;
import com.example.api.employer.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/employer/")
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;

    @GetMapping("favorites/employees")
    public ResponseEntity getLikeEmployee(@AuthenticationPrincipal final Long employerId) {
        EmployerIdRequest employerIdRequest = new EmployerIdRequest(employerId);
        List<LikeEmployeeDTO> result = employerService.getLikeEmployee(employerIdRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("businesses")
    public ResponseEntity getBusinessList(@AuthenticationPrincipal final Long employerId) {
        EmployerIdRequest employerIdRequest = new EmployerIdRequest(employerId);
        List<EmployerBusinessesRequest> businesses = employerService.getEmployerBusinessList(employerIdRequest);
        return ResponseEntity.ok(businesses);
    }
}
