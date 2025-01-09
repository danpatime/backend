package com.example.api.chat.controller.dto.response;

import com.example.api.chat.dto.ChatSummary;
import com.example.api.domain.ChatRoom;

import java.util.List;

public record ChatSummaryResponse(List<ChatRoom> chatRooms, List<ChatSummary> chatSummaries) {
}
