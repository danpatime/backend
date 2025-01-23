package com.example.api.chat.service;

import com.example.api.chat.controller.dto.request.ReadRequest;
import com.example.api.chat.repository.ChatRepository;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Chat;
import com.example.api.domain.ChatRoom;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import com.example.api.chat.controller.dto.request.ChatSendRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest()
@Transactional
@Rollback(false)
@ActiveProfiles("test")
class ChatServiceTest {
    @Autowired ChatService chatService;
    @Autowired ChatRoomRepository chatRoomRepository;
    @Autowired ChatRepository chatRepository;

    Account receiver;
    Account sender;
    ChatRoom givenChatRoom;
    Chat givenChat;

    @BeforeEach
    void setUp() {
        chatRoomRepository.deleteAll();
        chatRepository.deleteAll();
        setMembers(); // Members 설정 추가
        setChatRoom();
    }

    private void setMembers() {
        sender = new Account(); // 필요한 필드 설정
        sender.setAccountId(1L); // ID 설정 (테스트용)

        receiver = new Account(); // 필요한 필드 설정
        receiver.setAccountId(2L); // ID 설정 (테스트용)
    }
    private void setChatRoom() {
        ChatRoom chatRoom = new ChatRoom();
        givenChatRoom = chatRoomRepository.save(chatRoom);
    }

    @Test
    @Order(1)
    @DisplayName("채팅 전송")
    void sendChat() {
        // Given
        ChatSendRequest request = makeChatSendRequest();

        // When
        String result = chatService.sendChat(request);

        // Then
        List<Chat> chats = chatRepository.findAll();
        Chat savedMessage = chats.get(chats.size()-1);
        Assertions.assertFalse(savedMessage.getIsRead());
        Assertions.assertEquals("메세지 전송 성공~", result);
    }

    private ChatSendRequest makeChatSendRequest() {
        return new ChatSendRequest(
                givenChatRoom.getChatRoomId(),
                sender.getAccountId(),
                receiver.getAccountId(),
                "안녕 못한다."
        );
    }

    @Test
    @Order(2)
    @DisplayName("채팅 읽기")
    void readChat(){
        // Given
        ReadRequest request = new ReadRequest(1L, 2L);

        // When
        String result = chatService.readChats(request);

        // Then
        List<Chat> chats = chatRepository.findChats(1L, null);
        for (Chat chat : chats) {
            Assertions.assertTrue(chat.getIsRead());
        }
        Assertions.assertEquals("메세지 읽기 성공~", result);
    }
}