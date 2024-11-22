package com.example.api.possbileboard.controller;

import com.example.api.possbileboard.PossibleBoardService;
import com.example.api.possbileboard.dto.AddPossibleTimeCommand;
import com.example.api.possbileboard.dto.AddPossibleTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class PossibleBoardController {
    private final PossibleBoardService possibleBoardService;

    @PostMapping("/api/v1/possible-board")
    public ResponseEntity<?> addPossibleTimes(
            @RequestBody final AddPossibleTimeRequest addPossibleTimeRequest,
            final Long requestMemberId
    ) {
        final AddPossibleTimeCommand addPossibleTimeCommand = addPossibleTimeRequest.toCommand(requestMemberId);
        possibleBoardService.addPossibleBoard(addPossibleTimeCommand);
        return ResponseEntity.ok().build();
    }
}
