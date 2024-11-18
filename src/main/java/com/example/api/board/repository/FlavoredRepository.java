package com.example.api.board.repository;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.domain.Flavored;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavoredRepository extends JpaRepository<Flavored, Long> {
    @Query("select distinct new com.example.api.board.controller.domain.CategoryDTO(c.categoryId, c.categoryName) " +
            "from Flavored f join Category c on f.category.categoryId = c.categoryId where f.employee.accountId = :employeeId")
    List<CategoryDTO> findAllCategoryDTOByEmployeeId(@Param("employeeId") long employeeId);

    List<Flavored> findAllByEmployeeAccountId(@Param("employeeId")Long employeeId);
}
