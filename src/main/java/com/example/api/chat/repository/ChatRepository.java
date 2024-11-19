package com.example.api.chat.repository;

import com.example.api.chat.domain.ChatSummary;
import com.example.api.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String>, CustomChatRepository {
}
