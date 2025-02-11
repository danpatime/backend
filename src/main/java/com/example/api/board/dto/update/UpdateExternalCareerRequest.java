package com.example.api.board.dto.update;

import java.util.List;

public record UpdateExternalCareerRequest(
        List<ExternalCareerRequest> newExternalCareers
) {
    public record ExternalCareerRequest(
            Long subCategoryId,
            Integer workCount
    ){}
}