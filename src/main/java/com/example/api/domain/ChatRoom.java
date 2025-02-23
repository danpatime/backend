package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "CHAT_ROOM")
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ROOM_ID")
    private Long chatRoomId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "SUGGEST_ID", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
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