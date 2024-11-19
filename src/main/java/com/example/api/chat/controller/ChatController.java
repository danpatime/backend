package com.example.api.chat.controller;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.request.UserIdRequest;
import com.example.api.chat.controller.dto.response.ChatSummaryResponse;
import com.example.api.chat.service.ChatService;
import com.example.api.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatSendRequest chatRequest) {
        chatService.sendChat(chatRequest);
    }

    @MessageMapping("/read")
    public void readMessage(@Payload ReadRequest readRequest) {
        chatService.readChats(readRequest);
    }

    @GetMapping("/chat/summaries")
    public ResponseEntity<ChatSummaryResponse> getChatSummaries(@RequestBody UserIdRequest userIdRequest) {
        return ResponseEntity.ok(chatService.getChatSummaries(userIdRequest));
    }

    @GetMapping("/chat/room/{roomId}/chats")
    public ResponseEntity<List<Chat>> getMessages(@PathVariable("roomId") Long chatRoomId,
                                                   @RequestParam(value = "lastChatId", required = false) String chatId) {
        return ResponseEntity.ok(chatService.getChats(chatRoomId,chatId));
    }
}