package com.example.api.contracts;

import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusiness;
import com.example.api.contracts.update.UpdateContractConditionManager;
import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final OfferRepository offerRepository;
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final UpdateContractConditionManager updateContractConditionManager;

    @Transactional(readOnly = true)
    public List<SuggestedBusiness> getAllRelatedSuggests(final QueryAllSuggestsForMeCommand allSuggestsForMeCommand) {
        return offerRepository.queryEmployersSuggests(allSuggestsForMeCommand.employeeId());
    }

    @Transactional
    public void acceptSuggest(@Validated final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = offerRepository.findById(acceptSuggestCommand.suggestId())
                .orElseThrow();
        offerEmployment.succeeded();

        final Contract contract = contractMapper.notYetSucceeded(offerEmployment);
        contractRepository.save(contract);
    }

    @Transactional
    public void updateContract(@Validated final UpdateContractConditionCommand updateContractConditionCommand) {
        final Contract contract = contractRepository.findById(updateContractConditionCommand.contractId())
                .orElseThrow();
        updateContractConditionManager.updateContract(contract, updateContractConditionCommand);
    }
}
