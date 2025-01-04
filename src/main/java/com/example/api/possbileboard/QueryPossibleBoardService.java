package com.example.api.possbileboard;

import com.example.api.possbileboard.dto.PossiblePreview;
import com.example.api.possbileboard.dto.QueryPossibleBoardPreviewRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryPossibleBoardService {
    private final PossibleBoardRepository possibleBoardRepository;

    @Transactional(readOnly = true)
    public List<PossiblePreview> queryPreviews(final QueryPossibleBoardPreviewRequest request) {
        return possibleBoardRepository.queryPreviews(request);
    }
}
