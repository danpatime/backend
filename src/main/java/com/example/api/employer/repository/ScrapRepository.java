package com.example.api.employer.repository;

import com.example.api.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    @Query("select s from Scrap s where s.employer.employerId = :employerId")
    List<Scrap> findAllByEmployerId(@Param("employerId") long employerId);
}
