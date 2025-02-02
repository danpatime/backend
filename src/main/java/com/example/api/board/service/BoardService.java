package com.example.api.board.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.account.service.AccountService;
import com.example.api.board.dto.request.AddPossibleTimeCommand;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.board.dto.update.UpdateExternalCareerRequest;
import com.example.api.board.dto.update.UpdatePreferredCategoriesRequest;
import com.example.api.board.dto.response.*;
import com.example.api.board.dto.update.UpdatePreferredDistrictsRequest;
import com.example.api.board.entitiy.PossibleMapper;
import com.example.api.board.entitiy.PossibleTime;
import com.example.api.domain.*;
import com.example.api.domain.repository.*;
import com.example.api.board.repository.PossibleBoardRepository;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final AccountRepository accountRepository;
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final PossibleBoardRepository possibleBoardRepository;
    private final FlavoredCategoryRepository flavoredCategoryRepository;
    private final FlavoredDistrictRepository flavoredDistrictRepository;
    private final AccountService accountService;
    private final PossibleMapper possibleMapper;
    private final ObjectMapper objectMapper;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PersonalInfoResponse getPersonalInfoResponse(final EmployeeIdRequest employeeIdRequest){
        Account user = accountRepository.findByEmployeeId(employeeIdRequest.employeeId()).orElseThrow(() ->
                new BusinessException(ErrorCode.NULL_USER));
        return PersonalInfoResponse.of(user);
    }

    @Transactional(readOnly = true)
    public List<FlavoredDistrictResponse> getPreferredDistricts(final EmployeeIdRequest employeeIdRequest) {
        log.info("employeeIdRequest={}", employeeIdRequest.employeeId());
        List<FlavoredDistrictResponse> allByEmployeeId = flavoredDistrictRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
        log.info("allByEmployeeId: {}", allByEmployeeId);
        return allByEmployeeId;
    }

    @Transactional(readOnly = true)
    public List<FlavoredCategoryResponse> getPreferredCategories(final EmployeeIdRequest employeeIdRequest) {
        return flavoredCategoryRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional(readOnly = true)
    public List<WorkHourResponse> getWorkHours(final EmployeeIdRequest employeeIdRequest)  {
        return possibleBoardRepository.findScheduleFromCurrentMonth(employeeIdRequest.employeeId(), LocalDate.now().atStartOfDay());
    }

    @Transactional(readOnly = true)
    public List<ExternalCareerResponse> getExternalCareers(final EmployeeIdRequest employeeIdRequest)  {
        return externalCareerRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional(readOnly = true)
    public List<InternalCareerResponse> getInternalCareers(final EmployeeIdRequest employeeIdRequest)  {
        return offerEmploymentRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional
    public void addPossibleBoard(final AddPossibleTimeCommand addPossibleTimeCommand) {
        final List<PossibleTime> possibleTimes = addPossibleTimeCommand.possibleTimes().stream()
                .map(possibleTimeRange -> new PossibleTime(possibleTimeRange.startTime(), possibleTimeRange.endTime()))
                .collect(Collectors.toList());
        deleteDuplicatedPeriod(possibleTimes);
        final Account account = accountService.loadAccount(addPossibleTimeCommand.requestMemberId());
        addNewPeriod(account, possibleTimes);
    }

    private void deleteDuplicatedPeriod(final List<PossibleTime> possibleTimes) {
        possibleTimes.stream()
                .forEach(possibleTime -> possibleBoardRepository.deleteDuplicatedWorkTimeIncluded(possibleTime.getStartTime(), possibleTime.getEndTime()));
    }

    private void addNewPeriod(
            final Account account,
            final List<PossibleTime> possibleTimes
    ) {
        final List<PossibleBoard> possibleBoards = possibleTimes.stream()
                .map(possibleTime -> possibleMapper.toBoard(account, possibleTime))
                .toList();
        possibleBoardRepository.saveAll(possibleBoards);
    }

    @Transactional
    public List<FlavoredDistrictResponse> updatePreferredDistrict(
            final EmployeeIdRequest employeeIdRequest,
            final UpdatePreferredDistrictsRequest request
    ) {
        flavoredDistrictRepository.deleteByNotInIds(employeeIdRequest.employeeId(), request.districtIds());
        flavoredDistrictRepository.saveDistrictIds(employeeIdRequest.employeeId(), request.districtIds().toString());
        return flavoredDistrictRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional
    public List<FlavoredCategoryResponse> updatePreferredCategories(
            final EmployeeIdRequest employeeIdRequest,
            final UpdatePreferredCategoriesRequest request
    ) {
        flavoredCategoryRepository.deleteByNotInIds(employeeIdRequest.employeeId(), request.categoryIds());
        flavoredCategoryRepository.saveDistrictIds(employeeIdRequest.employeeId(), request.categoryIds().toString());
        return flavoredCategoryRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional
    public List<ExternalCareerResponse> updateExternalCareers(
            final EmployeeIdRequest employeeIdRequest,
            final UpdateExternalCareerRequest request) {

        Account user = accountRepository.findByEmployeeId(employeeIdRequest.employeeId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        List<ExternalCareerResponse> allExternalCareer = externalCareerRepository.findAllByEmployeeId(user.getAccountId());

        Set<Long> categoryIds = request.newExternalCareers().stream()
                .map(UpdateExternalCareerRequest.ExternalCareerRequest::categoryId)
                .collect(Collectors.toSet());

        Map<Long, Category> categoryMap = categoryRepository.findAllById(categoryIds).stream()
                .collect(Collectors.toMap(Category::getCategoryId, category -> category));

        List<ExternalCareer> newList = filterNewExternalCareers(request, allExternalCareer, categoryMap, user);
        List<Long> oldList = filterRemovableExternalCareers(request, allExternalCareer);

        if (!oldList.isEmpty()) {
            externalCareerRepository.deleteAllById(oldList);
        }
        if (!newList.isEmpty()) {
            externalCareerRepository.saveAll(newList);
        }

        return externalCareerRepository.findAllByEmployeeId(user.getAccountId());
    }

    @NotNull
    private static List<Long> filterRemovableExternalCareers(UpdateExternalCareerRequest request, List<ExternalCareerResponse> allExternalCareer) {
        List<Long> oldList = allExternalCareer.stream()
                .filter(savedAll -> request.newExternalCareers().stream()
                        .noneMatch(externalCareerRequest ->
                                savedAll.category().getCategoryId().equals(externalCareerRequest.categoryId()) &&
                                        savedAll.workCount().equals(externalCareerRequest.workCount())))
                .map(ExternalCareerResponse::externalCareerId)
                .collect(Collectors.toList());
        return oldList;
    }

    @NotNull
    private static List<ExternalCareer> filterNewExternalCareers(UpdateExternalCareerRequest request, List<ExternalCareerResponse> allExternalCareer, Map<Long, Category> categoryMap, Account user) {
        List<ExternalCareer> newList = request.newExternalCareers().stream()
                .filter(externalCareerRequest -> allExternalCareer.stream()
                        .noneMatch(savedAll ->
                                savedAll.category().getCategoryId().equals(externalCareerRequest.categoryId()) &&
                                        savedAll.workCount().equals(externalCareerRequest.workCount())))
                .map(updateRequest -> {
                    Category category = categoryMap.get(updateRequest.categoryId());
                    if (category == null) {
                        throw new BusinessException(ErrorCode.CATEGORY_EXCEPTION);
                    }
                    return new ExternalCareer(user, category, updateRequest.workCount());
                })
                .collect(Collectors.toList());
        return newList;
    }
}