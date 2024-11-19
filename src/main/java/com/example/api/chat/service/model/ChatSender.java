package com.example.api.chat.service.model;

import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.controller.dto.response.ReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatSender {
    private final SimpMessagingTemplate messagingTemplate;

    public void send(Message message) {
        messagingTemplate.convertAndSend("/room/"+ message.getRoomId(), message);
    }

    public void sendReadResponse(ReadRequest request) {
        ReadResponse readResponse = new ReadResponse(request.userId());
        messagingTemplate.convertAndSend("/room/" + request.roomId(), readResponse);
    }
}
