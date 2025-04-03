package com.example.api.chat.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.request.UserIdRequest;
import com.example.api.chat.controller.dto.response.ChatResponse;
import com.example.api.chat.controller.dto.response.ChatSummaryResponse;
import com.example.api.chat.dto.ChatSummary;
import com.example.api.chat.dto.ChatSummaryAndUserNickname;
import com.example.api.chat.repository.ChatRepository;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.chat.service.model.ChatSender;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.Chat;
import com.example.api.domain.ChatRoom;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AccountRepository accountRepository;

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
        Account user = accountRepository.findById(requestUserId.userId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        List<ChatRoom> chatRooms = chatRoomRepository.findByUserId(requestUserId.userId());
        List<Long> chatRoomIds = chatRooms.stream().map(ChatRoom::getChatRoomId).toList();
        List<ChatSummary> chatSummaries = chatRepository.aggregateChatSummaries(chatRoomIds, requestUserId.userId());

        // 3️⃣ `chatSummaries`를 `roomId` 기준으로 Map으로 변환 (빠른 조회용)
        Map<Long, ChatSummary> chatSummaryMap = chatSummaries.stream()
                .collect(Collectors.toMap(ChatSummary::roomId, Function.identity(), (existing, replacement) -> existing));

        List<ChatSummaryAndUserNickname> completedSummaries = chatRoomIds.stream()
                .map(roomId -> {
                    ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElse(null);

                    String[] nicknames = getUserAndOpponentNicknames(chatRoom, user);

                    // 기존의 ChatSummary와 닉네임을 포함한 새로운 객체 생성
                    ChatSummary chatSummary = chatSummaryMap.getOrDefault(roomId, new ChatSummary(roomId, null, null, null, 0L));

                    // ChatSummaryAndUserNickname 객체 반환
                    return ChatSummaryAndUserNickname.of(chatSummary, nicknames);
                })
                .toList();

        return new ChatSummaryResponse(completedSummaries);
    }

    private String[] getUserAndOpponentNicknames(ChatRoom chatRoom, Account requestUser) {
        Business business = chatRoom.getOfferEmployment().getBusiness();
        Account employee = chatRoom.getOfferEmployment().getEmployee();

        // 사용자와 상대방의 닉네임을 결정
        String userNickname;
        String opponentNickname;

        // 닉네임을 설정하는 조건문
        if (requestUser.getNickname().equals(business.getBusinessName())) {
            userNickname = business.getBusinessName();
            opponentNickname = employee.getNickname();
        } else {
            userNickname = employee.getNickname();
            opponentNickname = business.getBusinessName();
        }

        return new String[] {userNickname, opponentNickname};
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
