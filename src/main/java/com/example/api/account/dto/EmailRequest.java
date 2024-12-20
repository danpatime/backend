package com.example.api.account.dto.request;

import com.example.api.global.config.resolver.ValidEmail;

public record EmailRequest(@ValidEmail String email) {
}
