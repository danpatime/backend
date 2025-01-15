package com.example.api.board.dto.update;

import jakarta.validation.constraints.NotNull;

public record UpdateOpenStatusRequest(@NotNull boolean openStatus) implements UpdateAccountConditionCommand {
}
