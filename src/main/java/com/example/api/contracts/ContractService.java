package com.example.api.contracts;

import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusiness;
import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final OfferRepository offerRepository;
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Transactional(readOnly = true)
    public List<SuggestedBusiness> getAllRelatedSuggests(final QueryAllSuggestsForMeCommand allSuggestsForMeCommand) {
        return offerRepository.queryEmployersSuggests(allSuggestsForMeCommand.employeeId());
    }

    @Transactional
    public void acceptSuggest(final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = offerRepository.findById(acceptSuggestCommand.suggestId())
                .orElseThrow();
        offerEmployment.succeeded();

        final Contract contract = contractMapper.notYetSucceeded(offerEmployment);
        contractRepository.save(contract);
    }
}
