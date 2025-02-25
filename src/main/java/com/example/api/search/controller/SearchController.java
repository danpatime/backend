package com.example.api.search.controller;

import com.example.api.search.SearchService;
import com.example.api.search.dto.SearchCommand;
import com.example.api.search.dto.SearchRequest;
import com.example.api.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchAccounts(
            @RequestBody @Validated final SearchRequest request
    ) {
        final SearchCommand command = new SearchCommand(
                request.category(),
                request.startTime(),
                request.endTime()
        );
        final List<SearchResponse> results = searchService.searchAccounts(command);
        return ResponseEntity.ok(results);
    }
}