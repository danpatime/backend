package com.example.api.business;

import com.example.api.business.dto.CategoryInfo;
import com.example.api.domain.BusinessCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {
    @Query("SELECT new com.example.api.business.dto.CategoryInfo(bc.category.categoryId, bc.category.categoryName, (SELECT new com.example.api.business.dto.)) FROM BusinessCategory bc WHERE bc.business.businessId = :businessId")
    List<CategoryInfo> queryBusinessCategoriesById(@Param("businessId") final Long businessId);
}
