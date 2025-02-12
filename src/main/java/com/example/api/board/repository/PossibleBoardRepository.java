package com.example.api.board.repository;

import com.example.api.board.dto.response.WorkHourResponse;
import com.example.api.domain.Category;
import com.example.api.domain.Contract;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleBoardRepository extends JpaRepository<PossibleBoard, Long> {
    @Modifying
    @Query("DELETE FROM PossibleBoard possible WHERE (possible.startTime <= :endDateTime AND possible.endTime >= :startDateTime)")
    Integer deleteDuplicatedWorkTimeIncluded(@Param("startDateTime") final LocalDateTime startDateTimeIncluded,
                                          @Param("endDateTime") final LocalDateTime endDateTimeIncluded);

    @Query("select p from PossibleBoard p where p.employee.accountId = :employeeId and p.startTime >= :currentMonth")
    List<PossibleBoard> findScheduleFromCurrentMonth(@Param("employeeId")Long employeeId, @Param("currentMonth") LocalDateTime currentMonth);

    @Query("select p from PossibleBoard p where p.employee.accountId = :employeeId and p.startTime <= :endDateTime and p.endTime >= :startDateTime")
    Optional<PossibleBoard> findMatchingWorkHours(@Param("startDateTime") final LocalDateTime startDateTimeIncluded,
                                                  @Param("endDateTime") final LocalDateTime endDateTimeIncluded);
}