package com.example.api.aws.controller;

import com.example.api.aws.dto.UploadProfileRequest;
import com.example.api.aws.service.S3Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping(value = "/upload/profile", consumes = "multipart/form-data")
    public ResponseEntity<String> upload(@RequestParam("file") final MultipartFile file) {
        UploadProfileRequest request = new UploadProfileRequest(1L, file);
        return new ResponseEntity<>(s3Service.upload(request).path(), HttpStatus.OK);
    }
}