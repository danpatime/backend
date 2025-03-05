package com.example.api.search.controller;

import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.search.service.SearchService;
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
            @ModelAttribute @Validated final SearchRequest request,
            @RequestParam(required = false, defaultValue = "1") final Integer page
    ) {
        final List<SearchResponse> results = searchService.searchAccounts(request, new PageNumberRequest(page));
        return ResponseEntity.ok(results);
    }
}