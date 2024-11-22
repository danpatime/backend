package com.example.api.possbileboard;

import com.example.api.domain.Account;
import com.example.api.domain.PossibleBoard;
import org.springframework.stereotype.Service;

@Service
class PossibleMapper {

    PossibleBoard toBoard(final Account account, final PossibleTime possibleTime) {
        return new PossibleBoard(account, possibleTime.getStartTime(), possibleTime.getEndTime());
    }
}
