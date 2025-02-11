package com.example.api.board.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.board.dto.response.PersonalInfoResponse;
import com.example.api.board.dto.update.UpdatePersonalInfoRequest;
import com.example.api.board.entitiy.update.UpdateAccountConditionManager;
import com.example.api.domain.Account;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmployeeService {
    private final AccountRepository accountRepository;
    private final UpdateAccountConditionManager updateAccountConditionManager;

    @Transactional
    public Boolean changeOpenStatus(final EmployeeIdRequest employeeIdRequest, boolean openStatus) {
        return accountRepository.findByEmployeeId(employeeIdRequest.employeeId()).map(employee -> {
            employee.setOpenStatus(openStatus);
            accountRepository.save(employee);
            return true;
        }).orElse(false);
    }

    @Transactional
    public PersonalInfoResponse updatePersonalInfo(final EmployeeIdRequest employeeIdRequest, final UpdatePersonalInfoRequest personalInfoRequest) {
        Account employee = accountRepository.findByEmployeeId(employeeIdRequest.employeeId()).orElseThrow(() ->
                new BusinessException(ErrorCode.NULL_USER));
        updateAccountConditionManager.updateAccount(employee, getUpdateUserInfoRequest(personalInfoRequest));
        accountRepository.save(employee);
        return PersonalInfoResponse.of(employee);
    }

    private UpdatePersonalInfoRequest getUpdateUserInfoRequest(UpdatePersonalInfoRequest personalInfo) {
        return new UpdatePersonalInfoRequest(
                personalInfo.name(),
                personalInfo.sex(),
                personalInfo.age(),
                personalInfo.phoneNumber(),
                personalInfo.email(),
                personalInfo.nickname(),
                personalInfo.birthdate(),
                personalInfo.callTime()
        );
    }
}