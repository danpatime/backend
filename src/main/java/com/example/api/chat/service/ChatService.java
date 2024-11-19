package com.example.api.chat.service;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.repository.ChatRepository;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.chat.service.model.ChatSender;
import com.example.api.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatSender chatSender;

    @Transactional
    public String sendMessage(ChatSendRequest request) {
        Chat chat = saveChat(request);
        messageSender.send(savedMessage);
        return "메세지 전송 성공~";
    }

    private Chat saveChat(ChatSendRequest request) {
        Chat chat = Chat.from(request);
        return messageRepository.save(message);
    }

    @Transactional
    public String readMessages(ReadRequest request) {
        messageRepository.markMessagesAsRead(request.roomId(), request.memberId());
        messageSender.sendReadResponse(request);
        return "메세지 읽기 성공~";
    }

    public ChatSummaryResponse getChatSummaries(Member loginMember) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByMemberId(loginMember.getId());
        List<Long> chatRoomIds = chatRooms.stream().map(ChatRoom::getChatRoomId).toList();
        List<ChatMessageSummary> chatMessageSummaries = messageRepository.aggregateMessageSummaries(chatRoomIds, loginMember.getId());
        return new ChatSummaryResponse(chatRooms, chatMessageSummaries);
    }

    public List<Message> getMessages(Long chatRoomId, String lastMessageId) {
        return messageRepository.findMessages(chatRoomId,lastMessageId);
    }

    public List<ChatRoom> getChatRooms(Long memberId) {
        return chatRoomRepository.findByMemberId(memberId);
    }
}
