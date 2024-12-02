package com.example.api.board.service;

import com.example.api.board.controller.domain.request.EmployeeIdRequest;
import com.example.api.board.controller.domain.response.CategoryDTO;
import com.example.api.board.controller.domain.response.ExternalCareerDTO;
import com.example.api.board.controller.domain.response.MyInfoDTO;
import com.example.api.board.controller.domain.response.PossibleBoardDTO;
import com.example.api.domain.Account;
import com.example.api.domain.Category;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.Flavored;
import com.example.api.domain.PossibleBoard;
import com.example.api.domain.repository.EmployeeRepository;
import com.example.api.domain.repository.ExternalCareerRepository;
import com.example.api.domain.repository.FlavoredRepository;
import com.example.api.domain.repository.PossibleBoardRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final FlavoredRepository flavoredRepository;
    private final PossibleBoardRepository possibleBoardRepository;

    @Transactional
    public Boolean changeOpenStatus(final EmployeeIdRequest employeeIdRequest, boolean openStatus) {
        return employeeRepository.findByAccountId(employeeIdRequest.employeeId()).map(employee -> {
            employee.setOpenStatus(openStatus);
            employeeRepository.save(employee);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean updateUserInfo(final EmployeeIdRequest employeeIdRequest, MyInfoDTO myInfo) {
        return employeeRepository.findByAccountId(employeeIdRequest.employeeId()).map(employee -> {
            setUserInfo(employee, myInfo);
            employeeRepository.save(employee);

            updateExternalCareer(employee, myInfo.getExternalCareerList());
            updateFlavored(employee, myInfo.getFlavoredCategoryList());
            updatePossibleBoard(employee, myInfo.getPossibleBoardList());
            return true;
        }).orElse(false);
    }
    void setUserInfo(Account employee, MyInfoDTO myInfo) {
        employee.setName(myInfo.getName());
        employee.setSex(myInfo.getSex());
        employee.setAge(myInfo.getAge());
        employee.setPhoneNumber(myInfo.getPhone());
        employee.setEmail(myInfo.getEmail());
        employee.setNickname(myInfo.getNickname());
    }
    public void updateExternalCareer(Account employee, List<ExternalCareerDTO> newExternalCareerList) {
        List<ExternalCareer> existList = externalCareerRepository.findAllByEmployeeAccountId(employee.getAccountId());
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
    public void updateFlavored(Account employee, List<CategoryDTO> newCategoryList) {
        List<Flavored> existFlavored = flavoredRepository.findAllByEmployeeAccountId(employee.getAccountId());

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
    public void updatePossibleBoard(Account employee, List<PossibleBoardDTO> newPossibleBoard) {
        List<PossibleBoard> existPossibleBoard = possibleBoardRepository.findAllByEmployeeAccountId(employee.getAccountId());
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
