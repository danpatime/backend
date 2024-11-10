package com.example.api.board.service;

import com.example.api.board.controller.domain.*;
import com.example.api.board.repository.*;
import com.example.api.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final FlavoredRepository flavoredRepository;
    private final PossibleBoardRepository possibleBoardRepository;
    private final CategoryRepository categoryRepository;

    public Boolean changeOpenStatus(Long employeeId, boolean openStatus) {
        return employeeRepository.findByEmployeeId(employeeId).map(employee -> {
            employee.setOpenStatus(openStatus);
            employeeRepository.save(employee);
            return true;
        }).orElse(false);
    }
    public boolean updateUserInfo(Long employeeId, MyInfoDTO myInfo) {
        return employeeRepository.findByEmployeeId(employeeId).map(employee -> {
            setUserInfo(employee, myInfo);
            employeeRepository.save(employee);

            updateExternalCareer(employee, myInfo.getExternalCareerList());
            updateFlavored(employee, myInfo.getFlavoredCategoryList());
            updatePossibleBoard(employee, myInfo.getPossibleBoardList());
            return true;
        }).orElse(false);
    }
    void setUserInfo(Employee employee, MyInfoDTO myInfo) {
        Account account = employee.getAccount();

        account.setName(myInfo.getName());
        account.setSex(myInfo.getSex());
        account.setAge(myInfo.getAge());
        account.setPhoneNumber(myInfo.getPhone());
        account.setEmail(myInfo.getEmail());
        employee.setNickname(myInfo.getNickname());
    }
    public void updateExternalCareer(Employee employee, List<ExternalCareerDTO> newExternalCareerList) {
        List<ExternalCareer> existList = externalCareerRepository.findAllByEmployeeEmployeeId(employee.getEmployeeId());
        Set<ExternalCareerDTO> newSet = new HashSet<>(newExternalCareerList);

        Set<ExternalCareer> toDelete = existList.stream()
                .filter(exist -> newSet.stream()
                        .noneMatch(newDto -> newDto.getName().equals(exist.getName()) && newDto.getPeriod().equals(exist.getPeriod())))
                .collect(Collectors.toSet());

        Set<ExternalCareer> toAdd = newExternalCareerList.stream()
                .filter(dto -> existList.stream()
                        .noneMatch(exist -> exist.getName().equals(dto.getName()) && exist.getPeriod().equals(dto.getPeriod())))
                .map(dto -> new ExternalCareer(employee, dto.getName(), dto.getPeriod()))
                .collect(Collectors.toSet());

        externalCareerRepository.deleteAll(toDelete);
        externalCareerRepository.saveAll(toAdd);
    }
    public void updateFlavored(Employee employee, List<CategoryDTO> newCategoryList) {
        List<Flavored> existFlavored = flavoredRepository.findAllByEmployeeEmployeeId(employee.getEmployeeId());

        Set<Long> newCategoryIds = newCategoryList.stream()
                .map(CategoryDTO::getCategoryId)
                .collect(Collectors.toSet());

        Set<Flavored> toDelete = existFlavored.stream()
                .filter(exist -> !newCategoryIds.contains(exist.getCategory().getCategoryId()))
                .collect(Collectors.toSet());

        Set<Flavored> toAdd = newCategoryList.stream()
                .filter(dto -> existFlavored.stream()
                        .noneMatch(flavored -> flavored.getCategory().getCategoryId().equals(dto.getCategoryId())))
                .map(dto -> new Flavored(new Category(dto.getCategoryId(), dto.getCategoryName()), employee))
                .collect(Collectors.toSet());

        flavoredRepository.deleteAll(toDelete);
        flavoredRepository.saveAll(toAdd);
    }
    public void updatePossibleBoard(Employee employee, List<PossibleBoardDTO> newPossibleBoard) {
        List<PossibleBoard> existPossibleBoard = possibleBoardRepository.findAllByEmployeeEmployeeId(employee.getEmployeeId());
        Set<PossibleBoardDTO> newSet = new HashSet<>(newPossibleBoard);

        Set<PossibleBoard> toDelete = existPossibleBoard.stream()
                .filter(exist -> newSet.stream()
                        .noneMatch(newDto -> newDto.getStartTime().equals(exist.getStartTime())
                                && newDto.getEndTime().equals(exist.getEndTime())))
                .collect(Collectors.toSet());

        Set<PossibleBoard> ToAdd = newSet.stream()
                .filter(newDto -> existPossibleBoard.stream()
                        .noneMatch(exist -> newDto.getStartTime().equals(exist.getStartTime())
                                && newDto.getEndTime().equals(exist.getEndTime())))
                .map(dto -> new PossibleBoard(employee, dto.getStartTime(), dto.getEndTime()))
                .collect(Collectors.toSet());

        possibleBoardRepository.deleteAll(toDelete);
        possibleBoardRepository.saveAll(ToAdd);
    }
}
