package com.example.api.employer.service;

import com.example.api.board.repository.EmployeeRepository;
import com.example.api.board.repository.ExternalCareerRepository;
import com.example.api.board.repository.FlavoredRepository;
import com.example.api.domain.Employee;
import com.example.api.domain.Scrap;
import com.example.api.employer.controller.domain.LikeEmployeeDTO;
import com.example.api.employer.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public List<LikeEmployeeDTO> getLikeEmployee(Long employerId) {
        Set<Long> employeeIds = scrapRepository.findAllByEmployerId(employerId).stream()
                .map(scrap -> scrap.getEmployee().getEmployeeId())
                .collect(Collectors.toSet());
        List<Employee> likeEmployeeList = employeeRepository.findAllById(employeeIds);
        return likeEmployeeList.stream().map(
                employee -> new LikeEmployeeDTO(
                        employee.getEmployeeId(),
                        employee.getAccount().getName(),
                        employee.getNickname(),
                        employee.getAccount().getSex(),
                        employee.getAccount().getAge(),
                        employee.getStarPoint(),
                        employee.getWorkCount(),
                        externalCareerRepository.findAllDTOByEmployeeEmployeeId(employee.getEmployeeId()),
                        flavoredRepository.findAllCategoryDTOByEmployeeId(employee.getEmployeeId())
                )
        ).collect(Collectors.toList());
    }
}
