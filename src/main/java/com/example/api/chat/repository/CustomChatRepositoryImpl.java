package com.example.api.chat.repository;

import com.example.api.chat.dto.ChatSummary;
import com.example.api.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomChatRepositoryImpl implements CustomChatRepository {
    private final MongoTemplate mongoTemplate;
    

    @Override
    public void markChatsAsRead(Long chatRoomId, Long readBy) {
        Query query = new Query(Criteria.where("roomId").is(chatRoomId)
                .and("receiverId").is(readBy)
                .and("isRead").is(false));
        Update update = new Update();
        update.set("isRead", true);
        mongoTemplate.updateMulti(query, update, Chat.class);
    }

    @Override
    public List<Chat> findChats(Long chatRoomID, String lastChatId) {
        Query query = new Query(
                Criteria.where("roomId").is(chatRoomID)
        ).with(Sort.by(Sort.Direction.DESC, "_id")).limit(100);

        if (lastChatId != null) {
            query.addCriteria(Criteria.where("_id").lt(new ObjectId(lastChatId)));
        }

        return mongoTemplate.find(query, Chat.class);
    }

  

    @Override
    public List<ChatSummary> aggregateChatSummaries(List<Long> roomIds, Long memberId) {
        Criteria matchCriteria = Criteria.where("roomId").in(roomIds);
        AggregationOperation match = Aggregation.match(matchCriteria);

        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, "sendTime");

        AggregationOperation group = Aggregation.group("roomId")
                .first("roomId").as("roomId")
                .first("content").as("lastChatContent")
                .first("sendTime").as("lastChatTime")
                .sum(ConditionalOperators
                        .when(new Criteria().andOperator(
                                Criteria.where("receiverId").is(memberId),
                                Criteria.where("isRead").is(false)
                        ))
                        .then(1)
                        .otherwise(0))
                .as("numberOfUnreadChats");

        Aggregation aggregation = Aggregation.newAggregation(match, sort, group);

        AggregationResults<ChatSummary> results = mongoTemplate.aggregate(
                aggregation, "chat", ChatSummary.class);
        return new ArrayList<>(results.getMappedResults());
    }
}