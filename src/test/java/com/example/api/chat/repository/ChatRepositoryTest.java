package com.example.api.chat.repository;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import com.example.api.domain.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void testSaveChat() {
        ChatSendRequest request = new ChatSendRequest(1L, 1L, 2L, "테스트 메시지");
        Chat chat = Chat.from(request);

        // MongoDB에 저장
        chatRepository.save(chat);

        System.out.println("채팅 메시지 저장 완료!");
    }
}
