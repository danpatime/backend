package com.example.api.possbileboard.controller;

import com.example.api.possbileboard.PossibleBoardService;
import com.example.api.possbileboard.dto.AddPossibleTimeCommand;
import com.example.api.possbileboard.dto.AddPossibleTimeRequest;
import com.example.api.possbileboard.dto.PossibleDetailsResponse;
import com.example.api.possbileboard.dto.QueryPossibleDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/api/v1/possible-board/{possibleId}")
    public ResponseEntity<PossibleDetailsResponse> queryPossibleBoardTimes(
            @PathVariable(required = true) final Long possibleId
    ) {
        final QueryPossibleDetailsCommand command = new QueryPossibleDetailsCommand(possibleId);
        final PossibleDetailsResponse response = possibleBoardService.queryPossibleDetails(command);
        return ResponseEntity.ok(response);
    }
}
