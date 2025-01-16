<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> b4d1a08 (#65 test(OfferEmploymentServiceTest): 테스트 코드 작성)
package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
<<<<<<< HEAD
import com.querydsl.core.types.dsl.PathInits;
=======
>>>>>>> b4d1a08 (#65 test(OfferEmploymentServiceTest): 테스트 코드 작성)


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 731127133L;

<<<<<<< HEAD
    private static final PathInits INITS = PathInits.DIRECT2;

=======
>>>>>>> b4d1a08 (#65 test(OfferEmploymentServiceTest): 테스트 코드 작성)
    public static final QReview review = new QReview("review");

    public final QBaseEntity _super = new QBaseEntity(this);

<<<<<<< HEAD
    public final QContract contract;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

<<<<<<< HEAD
    public final QOfferEmployment offerEmployment;
=======
    public final QAccount employee;
>>>>>>> ace3d7a (#69 test(ReviewServiceTest): 테스트 코드 추가)

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final NumberPath<Integer> reviewStarPoint = createNumber("reviewStarPoint", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final QBusiness writer;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
<<<<<<< HEAD
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
        this.offerEmployment = inits.isInitialized("offerEmployment") ? new QOfferEmployment(forProperty("offerEmployment"), inits.get("offerEmployment")) : null;
=======
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
        this.writer = inits.isInitialized("writer") ? new QBusiness(forProperty("writer"), inits.get("writer")) : null;
>>>>>>> ace3d7a (#69 test(ReviewServiceTest): 테스트 코드 추가)
=======
    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Integer> reviewStarPoint = createNumber("reviewStarPoint", Integer.class);

    public final NumberPath<Long> suggestId = createNumber("suggestId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
>>>>>>> b4d1a08 (#65 test(OfferEmploymentServiceTest): 테스트 코드 작성)
    }

}

<<<<<<< HEAD
=======
>>>>>>> 326d0a2 (#65 feat(OfferEmploymentService): 서비스 코드 작성)
=======
>>>>>>> b4d1a08 (#65 test(OfferEmploymentServiceTest): 테스트 코드 작성)
