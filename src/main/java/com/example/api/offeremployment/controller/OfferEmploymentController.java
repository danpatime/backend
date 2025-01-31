package com.example.api.offeremployment.controller;

import com.example.api.offeremployment.OfferEmploymentService;
import com.example.api.offeremployment.dto.OfferEmploymentCompleteRequest;
import com.example.api.offeremployment.dto.OfferEmploymentRequest;
import com.example.api.offeremployment.dto.OfferEmploymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offeremployment")
@RequiredArgsConstructor
public class OfferEmploymentController {
    private final OfferEmploymentService offerEmploymentService;

    @PostMapping
    public ResponseEntity<OfferEmploymentResponse> sendOfferEmployment(
            @RequestBody final OfferEmploymentRequest offerEmploymentRequest
    ) {
        final OfferEmploymentResponse offerEmploymentResponse = offerEmploymentService.sendOfferEmployment(offerEmploymentRequest);
        return ResponseEntity.ok(offerEmploymentResponse);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeOfferEmployment(
            @RequestBody final OfferEmploymentCompleteRequest completeRequest
    ) {
        offerEmploymentService.completeOfferEmployment(completeRequest);
        return ResponseEntity.ok("성공적으로 종료되었습니다.");
    }
}