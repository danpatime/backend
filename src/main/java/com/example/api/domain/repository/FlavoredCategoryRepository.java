package com.example.api.domain.repository;

import com.example.api.board.dto.response.FlavoredCategoryResponse;
import com.example.api.domain.FlavoredCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavoredCategoryRepository extends JpaRepository<FlavoredCategory, Long> {
    @Query("select distinct new com.example.api.board.dto.response.FlavoredCategoryResponse(c.categoryId, c.categoryName, sc.subCategoryId, sc.subCategoryName) " +
            "from FlavoredCategory fc join fc.category c " +
            "join fc.subCategory sc " +
            "where fc.employee.accountId = :employeeId")
    List<FlavoredCategoryResponse> findAllByEmployeeId(@Param("employeeId") long employeeId);

    @Modifying
    @Query("DELETE FROM FlavoredCategory fc WHERE fc.category.categoryId NOT IN :ids AND fc.employee.accountId = :employeeId")
    void deleteByNotInIds(@Param("employeeId") Long employeeId, @Param("ids") List<Long> ids);

    @Modifying
    @Query(value = "INSERT INTO FLAVORED_CATEGORY (category_id, employee_id) " +
            "SELECT temp.category_id, :accountId " +
            "FROM JSON_TABLE(:categoryIds, '$[*]' COLUMNS (category_id BIGINT PATH '$')) AS temp " +
            "WHERE NOT EXISTS (" +
            "    SELECT 1 FROM FLAVORED_CATEGORY " +
            "    WHERE FLAVORED_CATEGORY.category_id = temp.category_id " +
            "    AND FLAVORED_CATEGORY.employee_id = :accountId" +
            ")",
            nativeQuery = true)
    void saveDistrictIds(@Param("accountId") Long accountId, @Param("categoryIds") String categoryIds);
}
