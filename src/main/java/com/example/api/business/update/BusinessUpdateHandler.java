package com.example.api.business.update;

import com.example.api.business.dto.ModifyBusinessCommand;
import com.example.api.domain.Business;

interface BusinessUpdateHandler {
    void update(final Business business, final ModifyBusinessCommand command);
}
