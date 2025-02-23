package com.example.api.chat.service;

import com.example.api.chat.controller.dto.request.UserIdRequest;
import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.response.ChatResponse;
import com.example.api.chat.controller.dto.response.ChatSummaryResponse;
import com.example.api.chat.dto.ChatSummary;
import com.example.api.chat.repository.ChatRepository;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.chat.service.model.ChatSender;
import com.example.api.domain.Chat;
import com.example.api.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.api.domain.Chat.utcToKstConvert;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatSender chatSender;

    @Transactional
    public String sendChat(final ChatSendRequest request) {
        log.info("Send chat request: {}", request);
        Chat savedChat = saveChat(request);
        chatSender.send(savedChat);
        return "메세지 전송 성공~";
    }

    private Chat saveChat(final ChatSendRequest request) {
        Chat chat = Chat.from(request);
        return chatRepository.save(chat);
    }

    @Transactional
    public String readChats(ReadRequest request) {
        chatRepository.markChatsAsRead(request.roomId(), request.receiverId());
        chatSender.sendReadResponse(request);
        return "메세지 읽기 성공~";
    }

    @Transactional(readOnly = true)
    public ChatSummaryResponse getChatSummaries(final UserIdRequest requestUserId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByUserId(requestUserId.userId());
        List<Long> chatRoomIds = chatRooms.stream().map(ChatRoom::getChatRoomId).toList();
        List<ChatSummary> chatSummaries = chatRepository.aggregateChatSummaries(chatRoomIds, requestUserId.userId());

        // 3️⃣ `chatSummaries`를 `roomId` 기준으로 Map으로 변환 (빠른 조회용)
        Map<Long, ChatSummary> chatSummaryMap = chatSummaries.stream()
                .collect(Collectors.toMap(ChatSummary::roomId, Function.identity(), (existing, replacement) -> existing));

        List<ChatSummary> completedSummaries = chatRoomIds.stream()
                .map(roomId -> chatSummaryMap.getOrDefault(roomId, new ChatSummary(roomId, null, null, null, 0L)))
                .collect(Collectors.toList());

        return new ChatSummaryResponse(completedSummaries);
    }

    @Transactional(readOnly = true)
    public List<ChatResponse> getChats(Long chatRoomId, String lastChatId) {
        return chatRepository.findChats(chatRoomId, lastChatId)
                .stream().map(chat -> new ChatResponse(
                        chat.getId(),
                        chat.getContent(),
                        chat.getRoomId(),
                        chat.getSenderId(),
                        chat.getReceiverId(),
                        utcToKstConvert(chat.getSendTime()),
                        chat.getIsRead()))
                .collect(toList());
    }

    public List<ChatRoom> getChatRooms(Long userId) {
        return chatRoomRepository.findByUserId(userId);
    }
}
