package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiry is a Querydsl query type for Inquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiry extends EntityPathBase<Inquiry> {

    private static final long serialVersionUID = 2045912162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiry inquiry = new QInquiry("inquiry");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> answerDate = createDateTime("answerDate", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final QAccount createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> inquiryId = createNumber("inquiryId", Long.class);

    public final EnumPath<Inquiry.InquiryStatus> inquiryStatus = createEnum("inquiryStatus", Inquiry.InquiryStatus.class);

    public final StringPath inquiryType = createString("inquiryType");

    public final StringPath subInquiryType = createString("subInquiryType");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QInquiry(String variable) {
        this(Inquiry.class, forVariable(variable), INITS);
    }

    public QInquiry(Path<? extends Inquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiry(PathMetadata metadata, PathInits inits) {
        this(Inquiry.class, metadata, inits);
    }

    public QInquiry(Class<? extends Inquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QAccount(forProperty("createdBy")) : null;
    }

}

