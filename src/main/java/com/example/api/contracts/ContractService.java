package com.example.api.contracts;

import com.example.api.account.repository.AccountRepository;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.board.repository.PossibleBoardRepository;
import com.example.api.contracts.dto.*;
import com.example.api.contracts.update.UpdateContractConditionManager;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.domain.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.offeremployment.entity.OfferEmploymentMapper;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final UpdateContractConditionManager updateContractConditionManager;
    private final PossibleBoardRepository possibleBoardRepository;
    private final AccountRepository accountRepository;
    private final OfferRepository offerRepository;
    private final OfferEmploymentMapper offerEmploymentMapper;

    @Transactional
    public void updateContract(@Validated final UpdateContractConditionCommand updateContractConditionCommand) {
        final Contract contract = loadContract(updateContractConditionCommand.contractId());
        updateContractConditionManager.updateContract(contract, updateContractConditionCommand);
    }

    @Transactional
    public void acceptContract(@Validated final AcceptContractCommand acceptContractCommand) {
        final Contract contract = loadContract(acceptContractCommand.contractId());
        OfferEmployment oe = offerRepository.findById(acceptContractCommand.contractId()).orElseThrow(() -> new BusinessException(ErrorCode.CONTRACT_EXCEPTION));
        oe.setStatus(ProposalStatus.COMPLETED);
        // 계약 시간에 따른 근무 가능 시간 변경
        updateAvailableWorkHours(acceptContractCommand, contract);
    }

    public void updateAvailableWorkHours(AcceptContractCommand acceptContractCommand, Contract contract) {
        Account user = accountRepository.findById(acceptContractCommand.contractId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        PossibleBoard matchingWorkHours = possibleBoardRepository.findMatchingWorkHours(contract.getContractStartTime(), contract.getContractEndTime())
                .orElseThrow(() -> new BusinessException(ErrorCode.POSSIBLE_TIME_NULL_EXCEPTION));

        // 예약 시간이 기존 근무 가능 시간과 완전히 일치하면 삭제 후 종료
        if (matchingWorkHours.getStartTime().equals(contract.getContractStartTime()) &&
                matchingWorkHours.getEndTime().equals(contract.getContractEndTime())) {
            possibleBoardRepository.delete(matchingWorkHours);
            return;
        }

        List<PossibleBoard> updatedWorkHours = new ArrayList<>();

        // 앞쪽 시간이 남아 있다면 추가
        if (!matchingWorkHours.getStartTime().equals(contract.getContractStartTime())) {
            updatedWorkHours.add(new PossibleBoard(user, matchingWorkHours.getStartTime(), contract.getContractStartTime()));
        }

        // 뒷쪽 시간이 남아 있다면 추가
        if (!matchingWorkHours.getEndTime().equals(contract.getContractEndTime())) {
            updatedWorkHours.add(new PossibleBoard(user, contract.getContractEndTime(), matchingWorkHours.getEndTime()));
        }

        // 기존 근무 가능 시간 삭제 후, 새로운 시간 저장
        possibleBoardRepository.delete(matchingWorkHours);
        if (!updatedWorkHours.isEmpty()) {
            possibleBoardRepository.saveAll(updatedWorkHours);
        }
    }


    private Contract loadContract(final Long contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public ContractDTO getContractInfo(final AcceptContractCommand contractStatusCommand) {
        BusinessInfoDTO businessDTO = contractRepository.findBusinessDTOByContractId(contractStatusCommand.contractId());
        EmployeeInfoDTO employeeDTO = contractRepository.findEmployeeDTOByContractId(contractStatusCommand.contractId());
        return new ContractDTO(businessDTO, employeeDTO);
    }
}