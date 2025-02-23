package com.example.api.chat.dto;

import org.jetbrains.annotations.Nullable;

import java.util.Date;

public record ChatSummary(Long roomId,
                          @Nullable String lastMessageId,
                          @Nullable String lastMessageContent,
                          @Nullable String lastMessageTime,
                          Long numberOfUnreadMessages) {
}
