package com.example.api.offeremployment.controller;

import com.example.api.offeremployment.OfferEmploymentService;
import com.example.api.offeremployment.dto.OfferEmploymentCompleteRequest;
import com.example.api.offeremployment.dto.OfferEmploymentRequest;
import com.example.api.offeremployment.dto.OfferEmploymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offeremployment")
@RequiredArgsConstructor
@Slf4j
public class OfferEmploymentController {
    private final OfferEmploymentService offerEmploymentService;

    @PostMapping
    public ResponseEntity<OfferEmploymentResponse> sendOfferEmployment(
            @RequestBody final OfferEmploymentRequest offerEmploymentRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error("인증 객체가 NULL입니다!");
        } else {
            log.info("현재 사용자: {}", authentication.getName());
            log.info("현재 권한: {}", authentication.getAuthorities());
        }


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

    @PostMapping("/refuse")
    public ResponseEntity<String> refuseOfferEmployment(
            @RequestBody final OfferEmploymentCompleteRequest completeRequest
    ) {
        offerEmploymentService.refuseOfferEmployment(completeRequest);
        return ResponseEntity.ok("성공적으로 거절되었습니다.");
    }
}