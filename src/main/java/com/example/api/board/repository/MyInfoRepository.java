package com.example.api.board.repository;

import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyInfoRepository extends JpaRepository<Account, Long> {
    @Query("select new com.example.api.board.controller.domain." +
            "MyInfoDTO(a.name, a.nickname, a.age, a.sex, a.email, a.phoneNumber, a.starPoint, a.workCount) " +
            "from Account a " +
            "where a.accountId = :EmployeeId")
    MyInfoDTO findMyInfoDTOById(@Param("EmployeeId") Long EmployeeId);
}
