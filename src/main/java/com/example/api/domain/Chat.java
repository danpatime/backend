package com.example.api.domain;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@EqualsAndHashCode
@Document(collection = "chat")
public class Chat {
    @Id
    private String id;
    private String content;
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private Date sendTime;
    private Boolean isRead;

    protected Chat() {
    }

    public static Chat from(ChatSendRequest chatSendRequest){
        Chat chat = new Chat();
        chat.content = chatSendRequest.content();
        chat.roomId = chatSendRequest.roomId();
        chat.senderId = chatSendRequest.senderId();
        chat.receiverId = chatSendRequest.receiverId();
        chat.sendTime = new Date();
        chat.isRead = false;
        return chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", roomId=" + roomId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", sendTime=" + sendTime +
                ", isRead=" + isRead +
                '}';
    }
}