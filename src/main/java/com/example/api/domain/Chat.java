package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ACCOUNT_UNIQUE_ID")
    private Account account;

    private String chatContent;

    @Column(name = "CHAT_DELETED", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

    @Column(name = "CHAT_REGISTER_DATE")
    private LocalDateTime chatRegisterDate;

    @PrePersist
    protected void onCreate() {
        this.chatRegisterDate = LocalDateTime.now();
    }
}
