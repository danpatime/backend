package com.example.api.chat.dto;

import java.util.Date;

public record ChatSummary(Long roomId, String lastMessageContent, Date lastMessageTime, Long numberOfUnreadMessages) {
}
