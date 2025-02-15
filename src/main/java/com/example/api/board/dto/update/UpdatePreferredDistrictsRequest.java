package com.example.api.board.dto.update;

import com.example.api.domain.Location;

import java.util.List;

public record UpdatePreferredDistrictsRequest(
        List<Location> locations
) implements UpdateAccountConditionCommand {
}
