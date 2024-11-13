package com.example.api.service;

import com.example.api.DTO.AccountDto;
import com.example.api.DTO.BusinessAccountDto;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.repository.AccountRepository;
import com.example.api.repository.BusinessRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final BusinessRepository businessRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, BusinessRepository businessRepository) {
        this.accountRepository = accountRepository;
        this.businessRepository = businessRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public void signupIndividual(AccountDto accountDto) {
        if (accountRepository.existsByLoginId(accountDto.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Account account = new Account();
        mapCommonFields(account, accountDto);
        accountRepository.save(account);

    }

    @Transactional
    public void signupBusiness(BusinessAccountDto businessAccountDto) {
        if (accountRepository.existsByLoginId(businessAccountDto.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (businessRepository.existsByRegistrationNumber(businessAccountDto.getRegistrationNumber())) {
            throw new IllegalArgumentException("이미 존재하는 사업자 등록번호입니다.");
        }

        Account account = new Account();
        mapCommonFields(account, businessAccountDto);
        accountRepository.save(account);

        Business business = new Business();
        business.setAccount(account); //연관관계 설정
        business.setRegistrationNumber(businessAccountDto.getRegistrationNumber());
        businessRepository.save(business);
    }

    private void mapCommonFields(Account account, AccountDto dto) {
        account.setLoginId(dto.getLoginId());
        account.setPassword(encryptPassword(dto.getPassword()));
        account.setName(dto.getName());
        account.setEmail(dto.getEmail());
        account.setPhoneNumber(dto.getPhone());
    }

    private  String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}