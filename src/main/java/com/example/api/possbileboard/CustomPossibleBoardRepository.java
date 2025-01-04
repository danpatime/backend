package com.example.api.possbileboard;

import com.example.api.possbileboard.dto.PossiblePreview;
import com.example.api.possbileboard.dto.QueryPossibleBoardPreviewRequest;
import java.util.List;

public interface CustomPossibleBoardRepository {
    List<PossiblePreview> queryPreviews(final QueryPossibleBoardPreviewRequest request);
}
