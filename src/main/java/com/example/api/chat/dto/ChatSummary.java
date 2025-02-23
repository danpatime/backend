package com.example.api.chat.dto;

import java.util.Date;

public record ChatSummary(Long roomId, String lastMessageId, String lastMessageContent, String lastMessageTime, Long numberOfUnreadMessages) {
}
