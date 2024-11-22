package com.example.api.possbileboard;

import com.example.api.account.AccountService;
import com.example.api.domain.Account;
import com.example.api.domain.PossibleBoard;
import com.example.api.possbileboard.dto.AddPossibleTimeCommand;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PossibleBoardService {
    private final PossibleBoardRepository possibleBoardRepository;
    private final AccountService accountService;
    private final PossibleMapper possibleMapper;

    @Transactional
    public void addPossibleBoard(final AddPossibleTimeCommand addPossibleTimeCommand) {
        final List<PossibleTime> possibleTimes = addPossibleTimeCommand.possibleTimes().stream()
                .map(possibleTimeRange -> new PossibleTime(possibleTimeRange.startTime(), possibleTimeRange.endTime()))
                .collect(Collectors.toList());
        deleteDuplicatedPeriod(possibleTimes);
        final Account account = accountService.loadAccount(addPossibleTimeCommand.requestMemberId());
        addNewPeriod(account, possibleTimes);
    }

    private void deleteDuplicatedPeriod(final List<PossibleTime> possibleTimes) {
        possibleTimes.stream()
                .forEach(possibleTime -> possibleBoardRepository.deleteDuplicatedWorkTimeIncluded(possibleTime.getStartTime(), possibleTime.getEndTime()));
    }

    private void addNewPeriod(final Account account, final List<PossibleTime> possibleTimes) {
        final List<PossibleBoard> possibleBoards = possibleTimes.stream()
                .map(possibleTime -> possibleMapper.toBoard(account, possibleTime))
                .toList();
        possibleBoardRepository.saveAll(possibleBoards);
    }
}
