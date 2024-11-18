package com.example.api.board.repository;

import com.example.api.board.controller.domain.PossibleBoardDTO;
import com.example.api.domain.PossibleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PossibleBoardRepository extends JpaRepository<PossibleBoard, Long> {
    List<PossibleBoard> findAllByEmployeeAccountId(Long employeeId);

    @Query("select new com.example.api.board.controller.domain.PossibleBoardDTO(p.possibleId, p.startTime, p.endTime) " +
            "from PossibleBoard p where p.employee.accountId = :employeeId")
    List<PossibleBoardDTO> findAllDTOByEmployeeAccountId(@Param("employeeId")Long employeeId);
}
