package com.example.api.board.entitiy;

import com.example.api.domain.Account;
import com.example.api.domain.Category;
import com.example.api.domain.Contract;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;
import com.example.api.board.dto.response.ExternalCareerResponse;
import com.example.api.board.dto.request.FlavoredCategory;
import com.example.api.board.dto.response.InternalCareerResponse;
import com.example.api.board.dto.response.PossibleDetailsResponse;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PossibleMapper {

    public PossibleBoard toBoard(final Account account, final PossibleTime possibleTime) {
        return new PossibleBoard(account, possibleTime.getStartTime(), possibleTime.getEndTime());
    }

    public PossibleDetailsResponse toPossibleDetailsResponse(final PossibleDetailsResponse possibleDetails, final List<Category> categories, final List<ExternalCareer> externalCareers, final List<Contract> internalCareeors) {
        return new PossibleDetailsResponse(
                possibleDetails.name(),
                possibleDetails.age(),
                possibleDetails.email(),
                possibleDetails.phoneNumber(),
                possibleDetails.recentlyUpdatedTime(),
                possibleDetails.possibleStartTime(),
                possibleDetails.possibleEndTime(),
                categories.stream()
                        .map(this::toFlavoredCategory)
                        .toList(),
                externalCareers.stream()
                        .map(this::toExternalCareerResponse)
                        .toList(),
                internalCareeors.stream()
                        .map(this::toInternalCareerResponse)
                        .toList(),
                possibleDetails.contractCount(),
                possibleDetails.starPoint().intValue()
        );
    }

    public ExternalCareerResponse toExternalCareerResponse(final ExternalCareer externalCareer) {
        return new ExternalCareerResponse(externalCareer.getId(), externalCareer.getCategory(), externalCareer.getWorkCount());
    }

    public FlavoredCategory toFlavoredCategory(final Category category) {
        return new FlavoredCategory(category.getCategoryId(), category.getCategoryName());
    }

    public InternalCareerResponse toInternalCareerResponse(final Contract contract) {
        String businessName = contract.getOfferEmployment().getBusiness().getBusinessName();
        return new InternalCareerResponse(contract.getContractId(), businessName, contract.getContractStartTime(), contract.getContractEndTime());
    }
}