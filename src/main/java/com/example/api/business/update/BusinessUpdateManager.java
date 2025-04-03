package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessUpdateManager {
    private final List<BusinessUpdateHandler> updateHandlers;

    public void update(final Business business, final ModifyBusinessCommand command) {
        updateHandlers.stream()
                .forEach(businessUpdateHandler -> businessUpdateHandler.update(business, command));
    }
}
