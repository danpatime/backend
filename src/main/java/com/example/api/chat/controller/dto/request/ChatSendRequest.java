package com.example.api.chat.controller.dto.request;

public record ChatSendRequest(Long roomId, Long senderId, Long receiverId, String content) {
}
