package com.example.api.search;

import com.example.api.search.dto.SearchCommand;
import com.example.api.search.dto.SearchResponse;
import com.example.api.domain.Account;
import com.example.api.domain.PossibleBoard;
import com.example.api.domain.Category;
import com.example.api.domain.repository.MyInfoRepository;
import com.example.api.domain.repository.PossibleBoardRepository;
import com.example.api.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class SearchServiceIntegrationTest {
    @Autowired
    private SearchService searchService;
    @Autowired
    private MyInfoRepository myInfoRepository;
    @Autowired
    private PossibleBoardRepository possibleBoardRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setName("장원영");
        account.setSex("Female");
        account.setAge(21);
        account.setStarPoint(4.8f);
        account.setWorkCount();
        myInfoRepository.save(account);

        Category category = new Category();
        category.setCategoryName("카페");
        categoryRepository.save(category);

        PossibleBoard possibleBoard = new PossibleBoard();
        possibleBoard.setEmployee(account);
        possibleBoard.setStartTime(LocalDateTime.of(2024, 11, 5, 9, 0));
        possibleBoard.setEndTime(LocalDateTime.of(2024, 11, 5, 18, 0));
        possibleBoardRepository.save(possibleBoard);
    }

    @Test
    void testSearchAccounts() {
        SearchCommand command = new SearchCommand("카페", "09:00", "18:00");

        List<SearchResponse> results = searchService.searchAccounts(command);

        assertEquals(1, results.size());
        SearchResponse response = results.get(0);
        assertEquals("장원영", response.name());
        assertEquals("Female", response.sex());
        assertEquals(21, response.age());
        assertEquals(4.8f, response.starPoint());
        assertEquals(15, response.workCount());
    }
}

