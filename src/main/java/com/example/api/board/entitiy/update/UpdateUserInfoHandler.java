package com.example.api.board.entitiy.update;

import com.example.api.board.dto.update.UpdateAccountConditionCommand;
import com.example.api.board.dto.update.UpdateUserInfoRequest;
import com.example.api.domain.Account;

public class UpdateUserInfoHandler implements UpdateAccountConditionHandler {
    @Override
    public void update(Account account, UpdateAccountConditionCommand updateAccountConditionCommand) {
        account.updateUserInfo((UpdateUserInfoRequest) updateAccountConditionCommand);
    }

    @Override
    public boolean supports(UpdateAccountConditionCommand command) {
        return command instanceof UpdateUserInfoRequest;
    }
}
