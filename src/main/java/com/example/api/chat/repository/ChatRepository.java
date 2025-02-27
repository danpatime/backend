package com.example.api.chat.repository;

import com.example.api.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String>, CustomChatRepository {
}