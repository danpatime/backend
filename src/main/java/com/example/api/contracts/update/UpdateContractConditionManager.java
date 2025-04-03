package com.example.api.contracts.update;

import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.domain.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateContractConditionManager {
    private final List<UpdateContractConditionHandler> updateContractConditionHandlers;

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateContract(final Contract contract, final UpdateContractConditionCommand updateContractConditionCommand) {
        updateContractConditionHandlers.stream()
                .forEach(updateContractHandler -> updateContractHandler.update(contract, updateContractConditionCommand));
    }
}