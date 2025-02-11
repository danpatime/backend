package com.example.api.domain.repository;

import com.example.api.domain.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {
    @Modifying
    @Query("delete from BusinessCategory bc where bc.business.businessId = :businessId")
    void deleteAllByBusinessId(@Param("businessId") Long businessId);
}
