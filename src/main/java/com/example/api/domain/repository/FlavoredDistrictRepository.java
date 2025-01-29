package com.example.api.domain.repository;

import com.example.api.board.dto.response.FlavoredDistrictResponse;
import com.example.api.domain.FlavoredDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlavoredDistrictRepository extends JpaRepository<FlavoredDistrict, Long> {
    @Query("select new com.example.api.board.dto.response.FlavoredCategoryResponse(fd.district.id, fd.district.district) " +
    "from FlavoredDistrict fd where fd.employee.accountId = :employeeId")
    List<FlavoredDistrictResponse> findAllByEmployeeId(Long employeeId);

    @Modifying
    @Query("DELETE FROM FlavoredDistrict p WHERE p.id NOT IN :ids AND p.employee.accountId = :employeeId")
    void deleteByNotInIds(@Param("employeeId") Long employeeId, @Param("ids") List<Long> ids);

    @Modifying
    @Query(value = "INSERT INTO FLAVORED_DISTRICT (CITY_DISTRICT_ID, EMPLOYEE_ID) " +
            "SELECT temp.district_id, :accountId " +
            "FROM JSON_TABLE(:districtIds, '$[*]' COLUMNS (district_id BIGINT PATH '$')) AS temp " +
            "WHERE NOT EXISTS (" +
            "    SELECT 1 FROM FLAVORED_DISTRICT " +
            "    WHERE FLAVORED_DISTRICT.CITY_DISTRICT_ID = temp.district_id " +
            "    AND FLAVORED_DISTRICT.EMPLOYEE_ID = :accountId" +
            ")",
            nativeQuery = true)
    void saveDistrictIds(@Param("accountId") Long accountId, @Param("districtIds") String districtIds);
}