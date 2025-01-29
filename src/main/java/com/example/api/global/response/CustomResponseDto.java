package com.example.api.global.response;

public record CustomResponseDto<T>(String code, T result) {
}
