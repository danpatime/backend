package com.example.api.possbileboard.controller;

import com.example.api.possbileboard.QueryPossibleBoardService;
import com.example.api.possbileboard.dto.PossiblePreview;
import com.example.api.possbileboard.dto.QueryPossibleBoardPreviewRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/possible-board")
public class PossibleBoardQueryController {
    private final QueryPossibleBoardService queryPossibleBoardService;

    @GetMapping
    public ResponseEntity<List<PossiblePreview>> queryPossibleBoard(
            @ModelAttribute @Valid final QueryPossibleBoardPreviewRequest request
    ) {
        return ResponseEntity.ok(queryPossibleBoardService.queryPreviews(request));
    }
}
