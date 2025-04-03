package com.example.api.chat.dto;

import jakarta.annotation.Nullable;

public record ChatSummaryAndUserNickname(
        Long roomId,
        @Nullable String lastMessageId,
        @Nullable String lastMessageContent,
        @Nullable String lastMessageTime,
        Long numberOfUnreadMessages,
        @Nullable String myNickname,
        @Nullable String opponentNickname
) {
    public static ChatSummaryAndUserNickname of(ChatSummary chatSummary, String[] nicknames){
        return new ChatSummaryAndUserNickname(
                chatSummary.roomId(),
                chatSummary.lastMessageId(),
                chatSummary.lastMessageContent(),
                chatSummary.lastMessageTime(),
                chatSummary.numberOfUnreadMessages(),
                nicknames[0],
                nicknames[1]
        );
    }
}
