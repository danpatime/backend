package com.example.api.reviewavailable;

import com.example.api.account.entity.UserRole;
import com.example.api.contracts.ContractRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.Contract;
import com.example.api.domain.OfferEmployment;
import com.example.api.reviewavailable.dto.ReviewAvailableCommand;
import com.example.api.reviewavailable.dto.ReviewAvailableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewAvailableServiceTest {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ReviewAvailableService reviewAvailableService;

    private ReviewAvailableCommand reviewAvailableCommand;
    private List<Contract> contracts;
    private Contract completedContract;
    private Contract incompleteContract;

    @BeforeEach
    public void setUp() {
        Account employer = new Account("Alice Employer", "alice@business.com", List.of(UserRole.EMPLOYER));
        Business business = new Business(employer, "Test Business");

        Account employee1 = new Account("John Doe", "john.doe@email.com", List.of(UserRole.EMPLOYEE));
        Account employee2 = new Account("Jane Smith", "jane.smith@email.com", List.of(UserRole.EMPLOYEE));

        OfferEmployment offerEmployment1 = new OfferEmployment(
                business,
                employee1,
                LocalDateTime.of(2025, 1, 1, 9, 0),
                LocalDateTime.of(2025, 1, 1, 18, 0),
                10000
        );

        OfferEmployment offerEmployment2 = new OfferEmployment(
                business,
                employee2,
                LocalDateTime.of(2025, 1, 2, 9, 0),
                LocalDateTime.of(2025, 1, 2, 18, 0),
                12000
        );

        completedContract = new Contract(
                offerEmployment1,
                LocalDateTime.of(2025, 1, 1, 9, 0),
                LocalDateTime.of(2025, 1, 1, 18, 0),
                10000,
                true
        );

        incompleteContract = new Contract(
                offerEmployment2,
                LocalDateTime.of(2025, 1, 2, 9, 0),
                LocalDateTime.of(2025, 1, 2, 18, 0),
                12000,
                false
        );

        contracts = List.of(completedContract, incompleteContract);

        reviewAvailableCommand = new ReviewAvailableCommand(business.getBusinessId());
    }

    @Test
    @DisplayName("완료된 계약만 조회할 수 있어야 한다")
    void shouldReturnCompletedContracts() {
        when(contractRepository.findAvailableReviewsByBusinessId(reviewAvailableCommand.businessId()))
                .thenReturn(
                        contracts.stream()
                                .filter(Contract::isContractSucceeded)
                                .map(contract -> new ReviewAvailableResponse(
                                        contract.getOfferEmployment().getEmployee().getAccountId(),
                                        contract.getOfferEmployment().getEmployee().getName()
                                ))
                                .toList()
                );
        List<ReviewAvailableResponse> responses = reviewAvailableService.getAvailableReviewTargets(reviewAvailableCommand);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(completedContract.getOfferEmployment().getEmployee().getName(), responses.get(0).employeeName());
        assertEquals(completedContract.getOfferEmployment().getEmployee().getAccountId(), responses.get(0).employeeId());

        verify(contractRepository, times(1)).findAvailableReviewsByBusinessId(reviewAvailableCommand.businessId());

    }
}




