package com.example.api.contracts.update;

import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.domain.Contract;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
class UpdateContractRangeHandler implements UpdateContractConditionHandler {
    @Override
    public void update(final Contract contract, final UpdateContractConditionCommand updateCommand) {
        if (Objects.nonNull(updateCommand.suggestStartDateTime())) {
            contract.updateStartDateTime(updateCommand.suggestStartDateTime());
        }
        if (Objects.nonNull(updateCommand.suggestEndDateTime())) {
            contract.updateEndDateTime(updateCommand.suggestEndDateTime());
        }

        if (contract.isValidContractRangeTime()) {
            throw new IllegalArgumentException();
        }
    }
}
