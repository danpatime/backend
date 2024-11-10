package com.example.api.employer.controller;

import com.example.api.employer.controller.domain.LikeEmployeeDTO;
import com.example.api.employer.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;

    @GetMapping("/api/v1/employees/favorites/{employerId}")
    public ResponseEntity getLikeEmployee(@PathVariable("employerId") Long employerId) {
        List<LikeEmployeeDTO> result = employerService.getLikeEmployee(employerId);
        return ResponseEntity.ok(result);
    }
}
