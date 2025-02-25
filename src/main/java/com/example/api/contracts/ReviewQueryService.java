package com.example.api.contracts;

import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.contracts.dto.QueryEmployersReviewCommand;
import java.util.List;

import com.example.api.review.dto.ReviewResponse;
import com.example.api.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> loadReviewsWithEmployerId(
            final QueryEmployersReviewCommand command,
            final PageNumberRequest pageNumberRequest
    ) {
        Pageable pageable = PageRequest.of(pageNumberRequest.page()-1, 15, Sort.by("createdDate").descending());
        return reviewRepository.loadReviewsByEmployerId(command.employerId(), pageable).getContent()
                .stream()
                .map(ReviewResponse::from)
                .toList();
    }
}
