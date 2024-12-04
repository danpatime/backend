package com.example.api.account.controller.dto.request;

import com.example.api.global.config.resolver.ValidEmail;

public record EmailCodeRequestDto(@ValidEmail String email, String code) {
}
