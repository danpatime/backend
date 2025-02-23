package com.example.api.chat.controller.dto.response;

public record ChatResponse(
        String id,
        String content,
        Long roomId,
        Long senderId,
        Long receiverId,
        String sendTime,
        Boolean isRead
) {
}
