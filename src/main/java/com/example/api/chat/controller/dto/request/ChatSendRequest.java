package com.example.api.chat.controller.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 필수
@AllArgsConstructor // 모든 필드를 포함하는 생성자
public class ChatSendRequest {
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private String content;
}
