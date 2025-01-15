package com.example.api.board.entitiy.update;

import com.example.api.board.dto.update.UpdateAccountConditionCommand;
import com.example.api.domain.Account;

public interface UpdateAccountConditionHandler {
    void update(final Account account, final UpdateAccountConditionCommand updateAccountConditionCommand);
    boolean supports(final UpdateAccountConditionCommand updateAccountConditionCommand);
}
