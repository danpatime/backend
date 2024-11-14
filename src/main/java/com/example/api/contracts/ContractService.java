package com.example.api.contracts;

import com.example.api.contracts.dto.AcceptContractCommand;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.contracts.dto.UpdateContractConditionCommand;
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
    public List<SuggestedBusinessResponse> getAllRelatedSuggests(final QueryAllSuggestsForMeCommand allSuggestsForMeCommand) {
        return offerRepository.queryEmployersSuggests(allSuggestsForMeCommand.employeeId());
    }

    @Transactional
    public void acceptSuggest(@Validated final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = loadOffer(acceptSuggestCommand.suggestId());
        offerEmployment.succeeded();

        final Contract contract = contractMapper.notYetSucceeded(offerEmployment);
        contractRepository.save(contract);
    }

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

    private OfferEmployment loadOffer(final Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow();
    }
}
