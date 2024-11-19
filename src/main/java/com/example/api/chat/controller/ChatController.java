package com.example.api.chat.controller;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatSendRequest messageRequest) {
        String result = chatService.sendMessage(messageRequest);
        System.out.println("result = " + result);
    }
}
