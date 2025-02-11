package com.example.api.domain.repository;

import com.example.api.board.dto.response.FlavoredCategoryResponse;
import com.example.api.board.repository.FlavoredCategoryRepositoryCustom;
import com.example.api.domain.FlavoredCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavoredCategoryRepository extends JpaRepository<FlavoredCategory, Long>, FlavoredCategoryRepositoryCustom {
    @Query("select distinct new com.example.api.board.dto.response.FlavoredCategoryResponse(c.categoryId, c.categoryName, sc.subCategoryId, sc.subCategoryName) " +
            "from FlavoredCategory fc join fc.category c " +
            "join fc.subCategory sc " +
            "where fc.employee.accountId = :employeeId")
    List<FlavoredCategoryResponse> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    @Modifying
    @Query("DELETE FROM FlavoredCategory fc WHERE fc.subCategory.subCategoryId NOT IN :ids AND fc.employee.accountId = :employeeId")
    void deleteByNotInIds(@Param("employeeId") Long employeeId, @Param("ids") List<Long> ids);
}