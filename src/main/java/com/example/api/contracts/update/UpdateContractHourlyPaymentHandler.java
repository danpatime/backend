package com.example.api.contracts.update;

import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.domain.Contract;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
class UpdateContractHourlyPaymentHandler implements UpdateContractConditionHandler {
    @Override
    public void update(final Contract contract, final UpdateContractConditionCommand updateContractConditionCommand) {
        final Integer payment = updateContractConditionCommand.suggestHourlyPayment();
        if (Objects.nonNull(payment)) {
            contract.updateHourlyPayment(payment);
        }
    }
}
