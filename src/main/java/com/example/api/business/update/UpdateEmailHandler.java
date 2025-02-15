package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(propagation = Propagation.MANDATORY)
class UpdateEmailHandler implements BusinessUpdateHandler {
    @Override
    public void update(Business business, ModifyBusinessCommand command) {
        if (Objects.nonNull(command.businessName())) {
            Account employer = business.getEmployer();
            employer.setEmail(command.email());
        }
    }
}