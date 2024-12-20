package com.example.api.account.dto;

import com.example.api.global.config.resolver.ValidEmail;

public record EmailCodeRequest(@ValidEmail String email, String code) {
}
