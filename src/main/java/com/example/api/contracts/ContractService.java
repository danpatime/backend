package com.example.api.contracts;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.contracts.dto.*;
import com.example.api.contracts.update.UpdateContractConditionManager;
import com.example.api.domain.ChatRoom;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final UpdateContractConditionManager updateContractConditionManager;
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;



    @Transactional
    public void updateContract(@Validated final UpdateContractConditionCommand updateContractConditionCommand) {
        final Contract contract = loadContract(updateContractConditionCommand.contractId());
        updateContractConditionManager.updateContract(contract, updateContractConditionCommand);
    }

    @Transactional
    public void acceptContract(@Validated final AcceptContractCommand acceptContractCommand) {
        final Contract contract = loadContract(acceptContractCommand.contractId());
        contract.succeed();
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

    @Transactional(readOnly = true)
    public List<ContractScheduleResponse> getContractSchedule(final EmployeeIdRequest employeeIdRequest) {
        return contractRepository.findContractScheduleByEmployeeId(employeeIdRequest.employeeId(), LocalDate.now().withDayOfMonth(1));
    }
}