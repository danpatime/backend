package com.example.api.board.entitiy;

import com.example.api.domain.*;

import org.springframework.stereotype.Service;

@Service
public class PossibleMapper {
    public PossibleBoard toBoard(final Account account, final PossibleTime possibleTime) {
        return new PossibleBoard(account, possibleTime.getStartTime(), possibleTime.getEndTime());
    }
}