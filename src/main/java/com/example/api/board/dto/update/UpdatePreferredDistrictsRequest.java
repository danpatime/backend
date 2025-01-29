package com.example.api.board.dto.update;

import java.util.List;

public record UpdatePreferredDistrictsRequest(
        List<Long> districtIds
) implements UpdateAccountConditionCommand {
}
