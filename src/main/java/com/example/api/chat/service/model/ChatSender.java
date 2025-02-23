package com.example.api.chat.service.model;

import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.response.ReadResponse;
import com.example.api.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatSender {
    private final SimpMessagingTemplate messagingTemplate;

    public void send(Chat chat) {
        messagingTemplate.convertAndSend("/room/"+ chat.getRoomId(), chat);
    }

    public void sendReadResponse(ReadRequest request) {
        ReadResponse readResponse = new ReadResponse(request.receiverId());
        messagingTemplate.convertAndSend("/room/" + request.roomId(), readResponse);
    }
}
