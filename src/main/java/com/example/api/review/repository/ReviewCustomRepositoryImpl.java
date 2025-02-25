package com.example.api.review.repository;

import com.example.api.domain.QAccount;
import com.example.api.domain.QBusiness;
import com.example.api.domain.QContract;
import com.example.api.domain.QReview;
import com.example.api.review.dto.ReviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.util.StringUtils.isNullOrEmpty;


@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final QReview review = QReview.review;
    private final QContract contract = QContract.contract;
    private final QAccount employee = QAccount.account;
    private final QBusiness business = QBusiness.business;

    public ReviewCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ReviewResponse> findReviews(String nickname, Pageable pageable) {
        List<ReviewResponse> content = queryFactory
                .select(Projections.constructor(ReviewResponse.class,
                        review.reviewId,
                        business.businessId,
                        business.businessName,
                        employee.accountId,
                        employee.nickname,
                        contract.contractStartTime,
                        contract.contractEndTime,
                        review.reviewStarPoint,
                        review.reviewContent
                ))
                .from(review)
                .join(review.contract, contract)
                .join(review.employee, employee)
                .join(review.writer, business)
                .where(nicknameContains(nickname))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(review.count())
                .from(review)
                .join(review.contract, contract)
                .join(review.employee, employee)
                .join(review.writer, business)
                .where(nicknameContains(nickname))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate nicknameContains(String nickname) {
        return !isNullOrEmpty(nickname) ? employee.nickname.contains(nickname) : null;
    }
}