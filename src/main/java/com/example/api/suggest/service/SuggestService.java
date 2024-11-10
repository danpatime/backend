package com.example.api.suggest.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestService {
    public void getSuggestStatus(long employeeId) {
    }
}
