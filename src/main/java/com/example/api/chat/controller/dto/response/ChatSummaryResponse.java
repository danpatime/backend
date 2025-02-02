package com.example.api.chat.controller.dto.response;

import com.example.api.chat.dto.ChatSummary;

import java.util.List;

public record ChatSummaryResponse(List<ChatSummary> chatSummaries) {
}