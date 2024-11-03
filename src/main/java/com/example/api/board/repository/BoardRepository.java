package com.example.api.board.repository;

import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e join e.account a on e.employeeId = a.id")
    MyInfoDTO findMyInfoDTOById(Long id);
}
