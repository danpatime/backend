package com.example.api.domain.repository;

import com.example.api.board.dto.response.FlavoredDistrictResponse;
import com.example.api.domain.FlavoredDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlavoredDistrictRepository extends JpaRepository<FlavoredDistrict, Long> {
    @Query("select new com.example.api.board.dto.response.FlavoredDistrictResponse(fd.location.sido, fd.location.sigugun, fd.location.dong) " +
    "from FlavoredDistrict fd where fd.employee.accountId = :employeeId")
    List<FlavoredDistrictResponse> findAllByEmployeeId(Long employeeId);

    @Modifying
    @Query("DELETE FROM FlavoredDistrict p WHERE p.employee.accountId = :employeeId")
    void deleteAllByEmployeeId(@Param("employeeId") Long employeeId);
}