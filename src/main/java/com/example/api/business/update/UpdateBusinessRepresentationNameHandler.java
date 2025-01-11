package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Business;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UpdateBusinessRepresentationNameHandler implements BusinessUpdateHandler {
    @Override
    public void update(Business business, ModifyBusinessCommand command) {
        if (Objects.nonNull(command.representationName())) {
            business.setRepresentationName(command.representationName());
        }
    }
}
