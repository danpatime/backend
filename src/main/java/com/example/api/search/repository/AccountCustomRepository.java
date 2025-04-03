package com.example.api.search.repository;

import com.example.api.search.dto.SearchRequest;
import com.example.api.search.dto.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountCustomRepository {
    Page<SearchResponse> findAvailableMembersByLocationAndCategoryAndDateTime(SearchRequest searchRequest, Pageable pageable);
}