package com.example.api.auth.verification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class verifyRequest {
    private String phoneNumber;
    private String enteredCode;
}

