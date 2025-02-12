package com.example.api.board.entitiy;

import com.example.api.board.dto.request.ContractDetailRequest;
import com.example.api.board.dto.response.WorkHourResponse;
import com.example.api.domain.*;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PossibleMapper {
    public PossibleBoard toBoard(final Account account, final PossibleTime possibleTime) {
        return new PossibleBoard(account, possibleTime.getStartTime(), possibleTime.getEndTime());
    }

    public List<WorkHourResponse> toWorkResponseFromPossibleBoard(final List<PossibleBoard> possibleBoard) {
        return possibleBoard.stream()
                .map(board -> new WorkHourResponse(
                        board.getPossibleId(),
                        null,
                        board.getStartTime(),
                        board.getEndTime(),
                        WorkHourResponse.Status.AVAILABLE))
                .collect(toList());
    }

    public List<WorkHourResponse> toWorkResponseFromContract(final List<ContractDetailRequest> contractRequest) {
        return contractRequest.stream()
                .map(contract -> new WorkHourResponse(
                        contract.id(),
                        contract.title(),
                        contract.startTime(),
                        contract.endTime(),
                        WorkHourResponse.Status.COMPLETED))
                .collect(toList());
    }
}