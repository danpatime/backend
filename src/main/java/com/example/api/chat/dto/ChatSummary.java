package com.example.api.chat.dto;


import jakarta.annotation.Nullable;

public record ChatSummary(
                          Long roomId,
                          @Nullable String lastMessageId,
                          @Nullable String lastMessageContent,
                          @Nullable String lastMessageTime,
                          Long numberOfUnreadMessages
) {
}