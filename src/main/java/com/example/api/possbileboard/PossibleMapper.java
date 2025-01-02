package com.example.api.possbileboard;

import com.example.api.domain.Account;
import com.example.api.domain.Category;
import com.example.api.domain.Contract;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;
import com.example.api.possbileboard.dto.ExternalCareerResponse;
import com.example.api.possbileboard.dto.FlavoredCategory;
import com.example.api.possbileboard.dto.InternalCareerResponse;
import com.example.api.possbileboard.dto.PossibleDetails;
import com.example.api.possbileboard.dto.PossibleDetailsResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class PossibleMapper {

    PossibleBoard toBoard(final Account account, final PossibleTime possibleTime) {
        return new PossibleBoard(account, possibleTime.getStartTime(), possibleTime.getEndTime());
    }

    PossibleDetailsResponse toPossibleDetailsResponse(final PossibleDetails possibleDetails, final List<Category> categories, final List<ExternalCareer> externalCareers, final List<Contract> internalCareeors) {
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

    ExternalCareerResponse toExternalCareerResponse(final ExternalCareer externalCareer) {
        return new ExternalCareerResponse(externalCareer.getId(), externalCareer.getName(), externalCareer.getPeriod());
    }

    FlavoredCategory toFlavoredCategory(final Category category) {
        return new FlavoredCategory(category.getCategoryId(), category.getCategoryName());
    }

    InternalCareerResponse toInternalCareerResponse(final Contract contract) {
        return new InternalCareerResponse(contract.getContractId(), contract.getContractHourlyPay(), contract.getContractStartTime(), contract.getContractEndTime());
    }
}
