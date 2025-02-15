package com.example.api.employer.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.Scrap;
import com.example.api.domain.repository.ExternalCareerRepository;
import com.example.api.domain.repository.FlavoredCategoryRepository;
import com.example.api.domain.Account;
import com.example.api.domain.repository.FlavoredDistrictRepository;
import com.example.api.employer.controller.dto.EmployerBusinessesRequest;
import com.example.api.employer.controller.dto.EmployerIdRequest;
import com.example.api.employer.controller.dto.FavoriteEmployeeRequest;
import com.example.api.employer.controller.dto.LikeEmployeeDTO;
import com.example.api.employer.repository.ScrapRepository;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final ScrapRepository scrapRepository;
    private final AccountRepository employeeRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final FlavoredCategoryRepository flavoredRepository;
    private final BusinessRepository businessRepository;
    private final AccountRepository accountRepository;
    private final FlavoredDistrictRepository flavoredDistrictRepository;

    @Transactional(readOnly = true)
    public List<LikeEmployeeDTO> getLikeEmployee(final EmployerIdRequest employerIdRequest) {
        Set<Long> employeeIds = scrapRepository.findAllByEmployerId(employerIdRequest.employerId()).stream()
                .map(scrap -> scrap.getEmployee().getAccountId())
                .collect(Collectors.toSet());
        List<Account> likeEmployeeList = employeeRepository.findAllById(employeeIds);
        return likeEmployeeList.stream().map(
                employee -> new LikeEmployeeDTO(
                        employee.getAccountId(),
                        employee.getName(),
                        employee.getNickname(),
                        employee.getSex(),
                        employee.getAge(),
                        employee.getStarPoint(),
                        employee.getWorkCount(),
                        externalCareerRepository.findAllByEmployeeId(employee.getAccountId()),
                        flavoredRepository.findAllByEmployeeId(employee.getAccountId()),
                        flavoredDistrictRepository.findAllByEmployeeId(employee.getAccountId())
                )
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployerBusinessesRequest> getEmployerBusinessList(final EmployerIdRequest employerIdRequest) {
        return businessRepository.findBusinessesByEmployerId(employerIdRequest.employerId());
    }

    @Transactional
    public String addFavoriteEmployee(final FavoriteEmployeeRequest favorite) {
        Account employee = accountRepository.findById(favorite.employeeId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        Account employer = accountRepository.findById(favorite.employerId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        Scrap newScrap = new Scrap(employee, employer);
        scrapRepository.save(newScrap);
        return "성공적으로 즐겨찾기를 완료하였습니다.";
    }

    @Transactional
    public String deleteFavoriteEmployee(final FavoriteEmployeeRequest favorite) {
        Account employee = accountRepository.findById(favorite.employeeId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        Account employer = accountRepository.findById(favorite.employerId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        Scrap deleteScrap = scrapRepository.findFirstByEmployeeAndEmployer(employee, employer)
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        scrapRepository.delete(deleteScrap);
        return "성공적으로 즐겨찾기를 삭제하였습니다.";
    }
}