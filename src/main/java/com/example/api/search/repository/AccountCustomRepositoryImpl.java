package com.example.api.search.repository;

import com.example.api.domain.QAccount;
import com.example.api.domain.QFlavoredCategory;
import com.example.api.domain.QFlavoredDistrict;
import com.example.api.domain.QPossibleBoard;
import com.example.api.search.dto.SearchRequest;
import com.example.api.search.dto.SearchResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class AccountCustomRepositoryImpl implements AccountCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final QAccount employee = QAccount.account;
    private final QPossibleBoard possibleBoard = QPossibleBoard.possibleBoard;
    private final QFlavoredCategory flavoredCategory = QFlavoredCategory.flavoredCategory;
    private final QFlavoredDistrict flavoredDistrict = QFlavoredDistrict.flavoredDistrict;

    public AccountCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<SearchResponse> findAvailableMembersByLocationAndCategoryAndDateTime(SearchRequest searchRequest, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builderConditionCheck(searchRequest, builder);

        List<SearchResponse> content = queryFactory.select(Projections.constructor(SearchResponse.class,
                        employee.accountId,
                        employee.name,
                        employee.sex,
                        employee.age,
                        employee.starPoint,
                        employee.workCount
                )).from(employee)
                .join(possibleBoard).on(employee.accountId.eq(possibleBoard.employee.accountId))
                .join(flavoredCategory).on(employee.accountId.eq(flavoredCategory.employee.accountId))
                .join(flavoredDistrict).on(employee.accountId.eq(flavoredDistrict.employee.accountId))
                .where(builder)
                .groupBy(employee.accountId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(employee.createdDate.desc())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(employee.accountId.countDistinct())
                .from(employee)
                .join(possibleBoard).on(employee.accountId.eq(possibleBoard.employee.accountId))
                .join(flavoredCategory).on(employee.accountId.eq(flavoredCategory.employee.accountId))
                .join(flavoredDistrict).on(employee.accountId.eq(flavoredDistrict.employee.accountId))
                .where(builder)
                .fetchOne()).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    private void builderConditionCheck(SearchRequest searchRequest, BooleanBuilder builder) {
        if (hasText(searchRequest.sido()) && hasText(searchRequest.sigugun()) && hasText(searchRequest.dong())) {
            builder.and(flavoredDistrict.location.sido.eq(searchRequest.sido()))
                    .and(flavoredDistrict.location.sigugun.eq(searchRequest.sigugun()))
                    .and(flavoredDistrict.location.dong.eq(searchRequest.dong()));
        }

        if (searchRequest.getStartDateTime() != null) {
            builder.and(possibleBoard.startTime.loe(searchRequest.getStartDateTime()));
        }

        if (searchRequest.getEndDateTime() != null) {
            builder.and(possibleBoard.endTime.goe(searchRequest.getEndDateTime()));
        }

        if (searchRequest.date() != null) {
            builder.and(possibleBoard.startTime.year().eq(searchRequest.date().getYear()))
                    .and(possibleBoard.startTime.month().eq(searchRequest.date().getMonthValue()))
                    .and(possibleBoard.startTime.dayOfMonth().eq(searchRequest.date().getDayOfMonth()));
        }

        if (searchRequest.startTime() != null) {
            builder.and(
                    Expressions.stringTemplate("TIME({0})", possibleBoard.startTime)
                            .loe(searchRequest.startTime().toString())
            );
        }

        if (searchRequest.endTime() != null) {
            builder.and(
                    Expressions.stringTemplate("TIME({0})", possibleBoard.endTime)
                            .goe(searchRequest.endTime().toString())
            );
        }

        if (searchRequest.subCategoryId() != null) {
            builder.and(flavoredCategory.subCategory.subCategoryId.eq(searchRequest.subCategoryId()));
        }
    }
}
