package com.example.api.contracts.update;

import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.domain.Contract;

interface UpdateContractConditionHandler {
    void update(final Contract contract, final UpdateContractConditionCommand updateContractConditionCommand);
}
