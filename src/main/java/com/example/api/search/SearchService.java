package com.example.api.search;

import com.example.api.search.dto.SearchCommand;
import com.example.api.search.dto.SearchResponse;
import com.example.api.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;

    @Transactional(readOnly = true)
    public List<SearchResponse> searchAccounts(final SearchCommand command) {
        final List<Account> accounts = searchRepository.searchAccountsByCategoryAndTime(
                command.category(),
                command.startTime(),
                command.endTime()
        );
        return accounts.stream()
                .map(account -> new SearchResponse(
                        account.getName(),
                        account.getSex(),
                        account.getAge(),
                        account.getStarPoint(),
                        account.getWorkCount()
                ))
                .collect(Collectors.toList());
    }
}