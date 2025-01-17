package com.example.api.employer.service;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.repository.EmployeeRepository;
import com.example.api.domain.repository.ExternalCareerRepository;
import com.example.api.domain.repository.FlavoredRepository;
import com.example.api.domain.Account;
import com.example.api.employer.controller.dto.EmployerBusinessesRequest;
import com.example.api.employer.controller.dto.LikeEmployeeDTO;
import com.example.api.employer.repository.ScrapRepository;
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
    private final EmployeeRepository employeeRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final FlavoredRepository flavoredRepository;
    private final BusinessRepository businessRepository;

    @Transactional(readOnly = true)
    public List<LikeEmployeeDTO> getLikeEmployee(final EmployeeIdRequest employeeIdRequest) {
        Set<Long> employeeIds = scrapRepository.findAllByEmployerId(employeeIdRequest.employeeId()).stream()
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
                        externalCareerRepository.findAllDTOByEmployeeAccountId(employee.getAccountId()),
                        flavoredRepository.findAllCategoryDTOByEmployeeId(employee.getAccountId())
                )
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployerBusinessesRequest> getEmployerBusinessList(final EmployeeIdRequest employeeIdRequest) {
        return businessRepository.findBusinessesByEmployeeId(employeeIdRequest.employeeId());
    }
}
