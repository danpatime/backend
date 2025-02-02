package com.example.api.aws.controller;

import com.example.api.aws.dto.UploadProfileRequest;
import com.example.api.aws.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping(value = "/upload/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(
            @AuthenticationPrincipal final Long userId,
            @RequestParam("file") final MultipartFile file) {
        log.info("userId={}", userId);
        UploadProfileRequest request = new UploadProfileRequest(userId, file);
        return new ResponseEntity<>(s3Service.upload(request).path(), HttpStatus.OK);
    }
}