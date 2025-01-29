package com.example.api.board.entitiy;

import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PossibleTime {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public PossibleTime(final LocalDateTime startTime, final LocalDateTime endTime) {
        validate(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void validate(final LocalDateTime startTime, final LocalDateTime endTime) {
        validateAfterStartTime(startTime, endTime);
    }

    public void validateAfterStartTime(final LocalDateTime startTime, final LocalDateTime endTime) {
        if (startTime.isBefore(endTime)) {
            return;
        }
        throw new BusinessException("종료 시간이 시작시간보다 이릅니다. 시작 시간: " + startTime + "종료 시간 : "  + endTime, ErrorCode.POSSIBLE_TIME_REGISTER_EXCEPTION);
    }
}
