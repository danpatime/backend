package com.example.api.domain;

import com.example.api.chat.controller.dto.request.ChatSendRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
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

    public Chat() {
    }

    public static Chat from(ChatSendRequest chatSendRequest){
        Chat chat = new Chat();
        chat.content = chatSendRequest.getContent();
        chat.roomId = chatSendRequest.getRoomId();
        chat.senderId = chatSendRequest.getSenderId();
        chat.receiverId = chatSendRequest.getReceiverId();
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

    public static String utcToKstConvert(Date sendTime){
        Instant utcInstant = sendTime.toInstant();

        // KST (UTC+9)로 변환
        ZonedDateTime kstTime = utcInstant.atZone(ZoneId.of("Asia/Seoul"));

        // "YYYY-MM-DD HH:MM" 형식으로 출력
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return kstTime.format(formatter);
    }
}