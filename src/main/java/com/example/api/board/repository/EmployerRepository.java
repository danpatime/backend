package com.example.api.board.repository;

import com.example.api.domain.Employee;
import com.example.api.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
