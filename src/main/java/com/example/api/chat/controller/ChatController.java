package com.example.api.chat.controller;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.request.UserIdRequest;
import com.example.api.chat.controller.dto.response.ChatResponse;
import com.example.api.chat.controller.dto.response.ChatSummaryResponse;
import com.example.api.chat.service.ChatService;
import com.example.api.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/send")
    public void sendMessage(@Payload final ChatSendRequest chatRequest) {
        chatService.sendChat(chatRequest);
    }

    @MessageMapping("/read")
    public void readMessage(@Payload final ReadRequest readRequest) {
        chatService.readChats(readRequest);
    }

    @GetMapping("/chat/summaries/{userId}")
    public ResponseEntity<ChatSummaryResponse> getChatSummaries(@PathVariable final Long userId) {
        return ResponseEntity.ok(chatService.getChatSummaries(new UserIdRequest(userId)));
    }

    @GetMapping("/chat/room/{roomId}/chats")
    public ResponseEntity<List<ChatResponse>> getMessages(@PathVariable("roomId") final Long chatRoomId,
                                                          @RequestParam(value = "lastChatId", required = false) final String chatId) {
        return ResponseEntity.ok(chatService.getChats(chatRoomId,chatId));
    }
}