package com.example.api.account.controller.dto.request;

import com.example.api.global.config.resolver.ValidEmail;

public record EmailRequestDto(@ValidEmail String email) {
}
