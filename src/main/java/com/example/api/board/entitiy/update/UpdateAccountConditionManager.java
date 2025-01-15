package com.example.api.board.entitiy.update;

import com.example.api.board.dto.update.UpdateAccountConditionCommand;
import com.example.api.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateAccountConditionManager {
    private final List<UpdateAccountConditionHandler> updateAccountConditionHandlers;

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateAccount(final Account account, final UpdateAccountConditionCommand command) {
        UpdateAccountConditionHandler handler = updateAccountConditionHandlers.stream()
                .filter(h -> h.supports(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적합한 핸들러가 없음"));

        handler.update(account, command);
    }
}