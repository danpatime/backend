package com.example.api.board.entitiy.update;

import com.example.api.board.dto.update.UpdateAccountConditionCommand;
import com.example.api.board.dto.update.UpdateOpenStatusRequest;
import com.example.api.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class UpdateOpenStatusHandler implements UpdateAccountConditionHandler {
    @Override
    public void update(Account account, UpdateAccountConditionCommand updateAccountConditionCommand) {
        account.updateOpenStatus((UpdateOpenStatusRequest) updateAccountConditionCommand);
    }

    @Override
    public boolean supports(UpdateAccountConditionCommand command) {
        return command instanceof UpdateOpenStatusRequest;
    }
}
