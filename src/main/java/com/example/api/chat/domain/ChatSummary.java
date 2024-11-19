package com.example.api.chat.domain;

import java.util.Date;

public record ChatSummary(Long roomId, String lastMessageContent, Date lastMessageTime, Long numberOfUnreadMessages) {
}
