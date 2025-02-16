package com.example.api.board.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.account.service.AccountService;
import com.example.api.board.dto.request.AddIntroductionRequest;
import com.example.api.board.dto.request.AddPossibleTimeCommand;
import com.example.api.board.dto.request.ContractDetailRequest;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.board.dto.update.UpdateExternalCareerRequest;
import com.example.api.board.dto.update.UpdatePreferredCategoriesRequest;
import com.example.api.board.dto.response.*;
import com.example.api.board.dto.update.UpdatePreferredDistrictsRequest;
import com.example.api.board.entitiy.PossibleMapper;
import com.example.api.board.entitiy.PossibleTime;
import com.example.api.contracts.ContractRepository;
import com.example.api.domain.*;
import com.example.api.domain.repository.*;
import com.example.api.board.repository.PossibleBoardRepository;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.github.jknack.handlebars.internal.lang3.tuple.Pair;
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
import java.util.stream.Stream;

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
    private final SubCategoryRepository subCategoryRepository;
    private final ContractRepository contractRepository;

    @Transactional(readOnly = true)
    public PersonalInfoResponse getPersonalInfoResponse(final EmployeeIdRequest employeeIdRequest){
        Account user = accountRepository.findByEmployeeId(employeeIdRequest.employeeId()).orElseThrow(() ->
                new BusinessException(ErrorCode.NULL_USER));
        return PersonalInfoResponse.of(user);
    }

    @Transactional(readOnly = true)
    public List<FlavoredDistrictResponse> getPreferredDistricts(final EmployeeIdRequest employeeIdRequest) {
        return flavoredDistrictRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional(readOnly = true)
    public List<FlavoredCategoryResponse> getPreferredCategories(final EmployeeIdRequest employeeIdRequest) {
        return flavoredCategoryRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional(readOnly = true)
    public List<WorkHourResponse> getWorkHours(final EmployeeIdRequest employeeIdRequest)  {
        List<PossibleBoard> boardScheduleFromCurrentMonth = possibleBoardRepository.findScheduleFromCurrentMonth(employeeIdRequest.employeeId(), LocalDate.now().atStartOfDay());
        List<ContractDetailRequest> contractScheduleFromCurrentMonth = contractRepository.findScheduleFromCurrentMonth(employeeIdRequest.employeeId(), LocalDate.now().atStartOfDay());
        List<WorkHourResponse> possibleBoardResponses = possibleMapper.toWorkResponseFromPossibleBoard(boardScheduleFromCurrentMonth);
        List<WorkHourResponse> contractResponses = possibleMapper.toWorkResponseFromContract(contractScheduleFromCurrentMonth);
        return Stream.concat(possibleBoardResponses.stream(), contractResponses.stream())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ExternalCareerResponse> getExternalCareers(final EmployeeIdRequest employeeIdRequest)  {
        return externalCareerRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional(readOnly = true)
    public List<InternalCareerResponse> getInternalCareers(final EmployeeIdRequest employeeIdRequest)  {
        return offerEmploymentRepository.findAllInternalCareerResponseByEmployeeId(employeeIdRequest.employeeId());
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
        flavoredDistrictRepository.deleteAllByEmployeeId(employeeIdRequest.employeeId());
        Account user = accountRepository.findById(employeeIdRequest.employeeId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        List<FlavoredDistrict> willSaveLocation = request.locations().stream()
                .map(location -> new FlavoredDistrict(location, user))
                .collect(Collectors.toList());
        flavoredDistrictRepository.saveAll(willSaveLocation);
        return flavoredDistrictRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional
    public List<FlavoredCategoryResponse> updatePreferredCategories(
            final EmployeeIdRequest employeeIdRequest,
            final UpdatePreferredCategoriesRequest request
    ) {
        List<Pair<Long, Long>> categoryPairs = request.categoryIds().stream()
                .map(cat -> Pair.of(cat.categoryId(), cat.subCategoryId()))
                .toList();
        List<Long> categoryIds = categoryPairs.stream().map(Pair::getLeft).collect(Collectors.toList());
        List<Long> subCategoryIds = categoryPairs.stream().map(Pair::getRight).collect(Collectors.toList());

        flavoredCategoryRepository.deleteByNotInIds(employeeIdRequest.employeeId(), subCategoryIds);
        flavoredCategoryRepository.saveAllCategoryIds(employeeIdRequest.employeeId(), categoryIds, subCategoryIds);
        return flavoredCategoryRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
    }

    @Transactional
    public List<ExternalCareerResponse> updateExternalCareers(
            final EmployeeIdRequest employeeIdRequest,
            final UpdateExternalCareerRequest request) {

        Account user = accountRepository.findByEmployeeId(employeeIdRequest.employeeId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        List<ExternalCareerResponse> allExternalCareer = externalCareerRepository.findAllByEmployeeId(user.getAccountId());

        Set<Long> subCategoryIds = request.newExternalCareers().stream()
                .map(UpdateExternalCareerRequest.ExternalCareerRequest::subCategoryId)
                .collect(Collectors.toSet());

        Map<Long, SubCategory> subCategoryMap = subCategoryRepository.findAllById(subCategoryIds).stream()
                .collect(Collectors.toMap(SubCategory::getSubCategoryId,  subCategory -> subCategory));

        List<ExternalCareer> newList = filterNewExternalCareers(request, allExternalCareer, subCategoryMap, user);
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
        return allExternalCareer.stream()
                .filter(savedAll -> request.newExternalCareers().stream()
                        .noneMatch(externalCareerRequest ->
                                savedAll.subCategory().subCategoryId().equals(externalCareerRequest.subCategoryId()) &&
                                        savedAll.workCount().equals(externalCareerRequest.workCount())))
                .map(ExternalCareerResponse::externalCareerId)
                .collect(Collectors.toList());
    }

    @NotNull
    private static List<ExternalCareer> filterNewExternalCareers(UpdateExternalCareerRequest request, List<ExternalCareerResponse> allExternalCareer, Map<Long, SubCategory> subCategoryMap, Account user) {
        return request.newExternalCareers().stream()
                .filter(externalCareerRequest -> allExternalCareer.stream()
                        .noneMatch(savedAll ->
                                savedAll.subCategory().subCategoryId().equals(externalCareerRequest.subCategoryId()) &&
                                        savedAll.workCount().equals(externalCareerRequest.workCount())))
                .map(updateRequest -> {
                    SubCategory subCategory = subCategoryMap.get(updateRequest.subCategoryId());
                    if (subCategory == null) {
                        throw new BusinessException(ErrorCode.CATEGORY_EXCEPTION);
                    }
                    return new ExternalCareer(user, subCategory, updateRequest.workCount());
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public IntroductionResponse getIntroduction(final EmployeeIdRequest employeeIdRequest) {
        return new IntroductionResponse(accountRepository.findIntroductionByAccountId(employeeIdRequest.employeeId()));
    }

    @Transactional
    public IntroductionResponse postIntroduction(
            final EmployeeIdRequest employeeIdRequest,
            final AddIntroductionRequest request) {
        Account user = accountRepository.findById(employeeIdRequest.employeeId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        user.setIntroduction(request.introduction());
        accountRepository.save(user);
        return new IntroductionResponse(request.introduction());
    }
}