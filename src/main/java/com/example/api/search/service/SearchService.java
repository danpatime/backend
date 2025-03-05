package com.example.api.search.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.domain.repository.ExternalCareerRepository;
import com.example.api.domain.repository.FlavoredCategoryRepository;
import com.example.api.domain.repository.FlavoredDistrictRepository;
import com.example.api.search.dto.SearchRequest;
import com.example.api.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final AccountRepository accountRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final FlavoredCategoryRepository flavoredCategoryRepository;
    private final FlavoredDistrictRepository flavoredDistrictRepository;

    @Transactional(readOnly = true)
    public List<SearchResponse> searchAccounts(final SearchRequest request, final PageNumberRequest pageNumberRequest) {
        Pageable pageable = PageRequest.of(pageNumberRequest.page() - 1, 5, Sort.by("createdDate"));
        List<SearchResponse> content = accountRepository.findAvailableMembersByLocationAndCategoryAndDateTime(request, pageable).getContent();

        Function<SearchResponse, SearchResponse> transformer = addDetailsToSearchResponse();

        return content.stream()
                .map(transformer)
                .collect(Collectors.toList());
    }

    @NotNull
    private Function<SearchResponse, SearchResponse> addDetailsToSearchResponse() {
        return response -> new SearchResponse(
                response.employeeId(),
                response.name(),
                response.sex(),
                response.age(),
                response.starPoint(),
                response.workCount(),
                externalCareerRepository.findAllByEmployeeId(response.employeeId()),
                flavoredCategoryRepository.findAllByEmployeeId(response.employeeId()),
                flavoredDistrictRepository.findAllByEmployeeId(response.employeeId())
        );
    }
}