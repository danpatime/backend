package com.example.api.chat.repository;

import com.example.api.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select c from ChatRoom c join c.offerEmployment oe " +
            "where oe.employee.accountId = :userId or oe.business.employer.accountId = :userId")
    List<ChatRoom> findByUserId(@Param("userId") Long userId);
}
