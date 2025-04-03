package com.example.api.chat.controller.dto.response;


import com.example.api.chat.dto.ChatSummaryAndUserNickname;

import java.util.List;

public record ChatSummaryResponse(List<ChatSummaryAndUserNickname> chatSummaries) {
}