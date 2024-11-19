package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "CHAT_ROOM")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "SUGGEST_ID")
    private OfferEmployment offerEmployment;

    @Column(name = "SUGGEST_GENERATED_DATE")
    private LocalDateTime suggestGeneratedDate;

    @PrePersist
    protected void onCreate() {
        this.suggestGeneratedDate = LocalDateTime.now();
    }

    public ChatRoom(OfferEmployment offerEmployment) {
        this.offerEmployment = offerEmployment;
        this.suggestGeneratedDate = LocalDateTime.now();
    }
}

