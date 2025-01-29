package com.example.api.domain.repository;

import com.example.api.board.dto.response.ExternalCareerResponse;
import com.example.api.domain.ExternalCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalCareerRepository extends JpaRepository<ExternalCareer, Long> {
    @Query("select new com.example.api.board.dto.response.ExternalCareerResponse(e.id, e.category, e.workCount) " +
            "from ExternalCareer e where e.employee.accountId = :employeeId")
    List<ExternalCareerResponse> findAllByEmployeeId(@Param("employeeId") Long employeeId);
}
