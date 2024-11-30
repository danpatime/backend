package com.example.api.business;

import com.example.api.domain.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface BusinessRepository extends JpaRepository<Business, Long> {
}
