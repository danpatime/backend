package com.example.api.possbileboard;

import com.example.api.board.controller.domain.response.PossibleBoardDTO;
import com.example.api.domain.Category;
import com.example.api.domain.Contract;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;
import com.example.api.possbileboard.dto.PossibleDetails;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleBoardRepository extends JpaRepository<PossibleBoard, Long> {
    @Modifying
    @Query("DELETE FROM PossibleBoard possible WHERE (possible.startTime <= :endDateTime AND possible.endTime >= :startDateTime)")
    Long deleteDuplicatedWorkTimeIncluded(@Param("startDateTime") final LocalDateTime startDateTimeIncluded,
                                          @Param("endDateTime") final LocalDateTime endDateTimeIncluded);

    @Query("SELECT new com.example.api.possbileboard.dto.PossibleDetails(p.employee.name, p.employee.age, p.employee.email, p.employee.phoneNumber, p.updatedDate, p.startTime, p.endTime, COUNT (p), CAST(COALESCE(AVG(r.reviewStarPoint),0) as INTEGER)) FROM PossibleBoard p INNER JOIN Account a INNER JOIN Contract c INNER JOIN Review r WHERE p.possibleId = :possibleId GROUP BY p.employee.name, p.employee.age, p.employee.email, p.employee.phoneNumber, p.updatedDate, p.startTime, p.endTime")
    PossibleDetails queryPossibleDetails(@Param("possibleId") final Long possibleId);

    @Query("SELECT f.category FROM Flavored f JOIN Account a JOIN PossibleBoard p WHERE p.possibleId = :possibleId")
    List<Category> queryFlavoredCategories(@Param("possibleId") final Long possibleId);

    @Query("SELECT ex FROM ExternalCareer ex JOIN PossibleBoard p ON ex.employee.accountId = p.employee.accountId WHERE p.possibleId = :possibleId")
    List<ExternalCareer> queryExternalCareeors(@Param("possibleId") final Long possibleId);

    @Query("SELECT c FROM Contract c JOIN PossibleBoard p ON p.employee.accountId = c.offerEmployment.employee.accountId WHERE c.offerEmployment.employee.accountId = :possibleId AND c.contractSucceeded = TRUE ")
    List<Contract> queryInternalCareeors(@Param("possibleId") final Long possibleId);

    List<PossibleBoard> findAllByEmployeeAccountId(Long employeeId);

    @Query("select new com.example.api.board.controller.domain.response.PossibleBoardDTO(p.possibleId, p.startTime, p.endTime) " +
            "from PossibleBoard p where p.employee.accountId = :employeeId")
    List<PossibleBoardDTO> findAllDTOByEmployeeAccountId(@Param("employeeId")Long employeeId);

}
