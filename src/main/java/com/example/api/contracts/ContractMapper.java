package com.example.api.contracts;

import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
class ContractMapper {
    public Contract notYetSucceeded(final OfferEmployment offerEmployment) {
        return new Contract(
                offerEmployment,
                offerEmployment.getSuggestStartTime(),
                offerEmployment.getSuggestEndTime(),
                offerEmployment.getSuggestHourlyPay(),
                false
        );
    }
}
