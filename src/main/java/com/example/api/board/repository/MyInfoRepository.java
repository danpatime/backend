package com.example.api.board.repository;

import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyInfoRepository extends JpaRepository<Employee, Long> {
    @Query("select new com.example.api.board.controller.domain." +
            "MyInfoDTO(e.account.name, e.nickname, a.age, a.sex, a.email, a.phoneNumber, e.starPoint, e.workCount) " +
            "from Employee e join e.account a on e.employeeId = a.id " +
            "where e.employeeId = :EmployeeId")
    MyInfoDTO findMyInfoDTOById(@Param("EmployeeId") Long EmployeeId);
}
