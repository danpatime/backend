package com.example.api.chat.repository;

import com.example.api.chat.domain.ChatSummary;
import com.example.api.domain.Chat;

import java.util.List;

public interface CustomChatRepository {
    void markChatsAsRead(Long chatRoomId, Long readBy);
    List<Chat> findChats(Long chatRoomId, String lastChatId);
    List<ChatSummary> aggregateChatSummaries(List<Long> chatRoomIds, Long memberId);
}
