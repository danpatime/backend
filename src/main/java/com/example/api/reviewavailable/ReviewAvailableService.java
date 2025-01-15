package com.example.api.reviewavailable;

import com.example.api.contracts.ContractRepository;
import com.example.api.contracts.dto.BusinessInfoDTO;
import com.example.api.contracts.dto.EmployeeInfoDTO;
import com.example.api.domain.Contract;
import com.example.api.reviewavailable.dto.ReviewAvailableCommand;
import com.example.api.reviewavailable.dto.ReviewAvailableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewAvailableService {
    private final ContractRepository contractRepository;

    @Transactional
    public List<ReviewAvailableResponse> getAvailableReviewTargets(
            final ReviewAvailableCommand command
    ) {
        return contractRepository.findAvailableReviewsByBusinessId(command.businessId());
    }
}
