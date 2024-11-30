package com.example.api.chat.service;

import com.example.api.chat.controller.dto.request.UserIdRequest;
import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.response.ChatSummaryResponse;
import com.example.api.chat.domain.ChatSummary;
import com.example.api.chat.repository.ChatRepository;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.chat.service.model.ChatSender;
import com.example.api.domain.Chat;
import com.example.api.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatSender chatSender;

    @Transactional
    public String sendChat(ChatSendRequest request) {
        Chat savedChat = saveChat(request);
        chatSender.send(savedChat);
        return "메세지 전송 성공~";
    }

    private Chat saveChat(ChatSendRequest request) {
        Chat chat = Chat.from(request);
        return chatRepository.save(chat);
    }

    @Transactional
    public String readChats(ReadRequest request) {
        chatRepository.markChatsAsRead(request.roomId(), request.userId());
        chatSender.sendReadResponse(request);
        return "메세지 읽기 성공~";
    }

    @Transactional(readOnly = true)
    public ChatSummaryResponse getChatSummaries(UserIdRequest requestUserId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByUserId(requestUserId.userId());
        List<Long> chatRoomIds = chatRooms.stream().map(ChatRoom::getChatRoomId).toList();
        List<ChatSummary> chatSummaries = chatRepository.aggregateChatSummaries(chatRoomIds, requestUserId.userId());
        return new ChatSummaryResponse(chatRooms, chatSummaries);
    }

    @Transactional(readOnly = true)
    public List<Chat> getChats(Long chatRoomId, String lastChatId) {
        return chatRepository.findChats(chatRoomId,lastChatId);
    }

    public List<ChatRoom> getChatRooms(Long userId) {
        return chatRoomRepository.findByUserId(userId);
    }
}
