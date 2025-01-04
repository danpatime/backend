package com.example.api.possbileboard;

import com.example.api.domain.QPossibleBoard;
import com.example.api.possbileboard.dto.PossiblePreview;
import com.example.api.possbileboard.dto.QueryPossibleBoardPreviewRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CustomPossibleBoardRepositoryImpl implements CustomPossibleBoardRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPossibleBoard qPossibleBoard = QPossibleBoard.possibleBoard;

    @Override
    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    public List<PossiblePreview> queryPreviews(final QueryPossibleBoardPreviewRequest request) {
        return jpaQueryFactory.from(qPossibleBoard)
                .select(Projections.constructor(PossiblePreview.class, qPossibleBoard.possibleId))
                .join(qPossibleBoard.employee).fetchJoin()
                .where(containsName(request),
                        containsDateRange(request),
                        containsAge(request))
                .fetch();
    }

    private BooleanExpression containsAge(final QueryPossibleBoardPreviewRequest request) {
        if (Objects.nonNull(request.age())) {
            return qPossibleBoard.employee.age.eq(request.age());
        }
        return null;
    }

    private BooleanExpression containsName(final QueryPossibleBoardPreviewRequest request) {
        if (Objects.nonNull(request.name())) {
            return qPossibleBoard.employee.name.contains(request.name());
        }
        return null;
    }

    private BooleanExpression containsDateRange(final QueryPossibleBoardPreviewRequest request) {
        if (Objects.nonNull(request.startWorkTime()) && Objects.nonNull(request.endWorkTime())) {
            return qPossibleBoard.startTime.before(request.startWorkTime())
                    .and(qPossibleBoard.endTime.after(request.endWorkTime()));
        }
        return null;
    }
}
