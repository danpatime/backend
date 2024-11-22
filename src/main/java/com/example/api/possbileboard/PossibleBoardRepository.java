package com.example.api.possbileboard;

import com.example.api.domain.PossibleBoard;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface PossibleBoardRepository extends JpaRepository<PossibleBoard, Long> {
    @Modifying
    @Query("DELETE FROM PossibleBoard possible WHERE (possible.startTime <= :endDateTime AND possible.endTime >= :startDateTime)")
    Long deleteDuplicatedWorkTimeIncluded(@Param("startDateTime") final LocalDateTime startDateTimeIncluded, @Param("endDateTime") final LocalDateTime endDateTimeIncluded);
}
