package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusiness is a Querydsl query type for Business
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusiness extends EntityPathBase<Business> {

    private static final long serialVersionUID = 1647868037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusiness business = new QBusiness("business");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<BusinessCategory, QBusinessCategory> businessCategories = this.<BusinessCategory, QBusinessCategory>createList("businessCategories", BusinessCategory.class, QBusinessCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> businessId = createNumber("businessId", Long.class);

    public final StringPath businessName = createString("businessName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QAccount employer;

    public final QLocation location;

    public final StringPath openDate = createString("openDate");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final StringPath representationName = createString("representationName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QBusiness(String variable) {
        this(Business.class, forVariable(variable), INITS);
    }

    public QBusiness(Path<? extends Business> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusiness(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusiness(PathMetadata metadata, PathInits inits) {
        this(Business.class, metadata, inits);
    }

    public QBusiness(Class<? extends Business> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employer = inits.isInitialized("employer") ? new QAccount(forProperty("employer"), inits.get("employer")) : null;
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
    }

}

