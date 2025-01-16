package com.example.api.search;

import com.example.api.search.dto.SearchCommand;
import com.example.api.search.dto.SearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SearchIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearchService searchService;

    @Test
    void testSearchAccountsFromService() {
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 5, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 5, 17, 0);

        SearchCommand command = new SearchCommand("IT Services", startTime, endTime);

        List<SearchResponse> results = searchService.searchAccounts(command);

        assertEquals(1, results.size());
        SearchResponse response = results.get(0);
        assertEquals("John Doe", response.name());
        assertEquals("Male", response.sex());
        assertEquals(30, response.age());
        assertEquals(3.5f, response.starPoint());
        assertEquals(3, response.workCount());
    }

    @Test
    void testSearchAccountsFromController() throws Exception {
        mockMvc.perform(post("/api/search/search")
                        .contentType("application/json")
                        .content("""
                        {
                          "category": "IT Services",
                          "startTime": "2024-11-05T09:00:00",
                          "endTime": "2024-11-05T17:00:00"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].sex").value("Male"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[0].starPoint").value(3.5))
                .andExpect(jsonPath("$[0].workCount").value(3));
    }
}
