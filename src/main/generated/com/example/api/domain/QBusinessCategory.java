package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessCategory is a Querydsl query type for BusinessCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessCategory extends EntityPathBase<BusinessCategory> {

    private static final long serialVersionUID = -861191005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessCategory businessCategory = new QBusinessCategory("businessCategory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBusiness business;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSubCategory subCategory;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QBusinessCategory(String variable) {
        this(BusinessCategory.class, forVariable(variable), INITS);
    }

    public QBusinessCategory(Path<? extends BusinessCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessCategory(PathMetadata metadata, PathInits inits) {
        this(BusinessCategory.class, metadata, inits);
    }

    public QBusinessCategory(Class<? extends BusinessCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.business = inits.isInitialized("business") ? new QBusiness(forProperty("business"), inits.get("business")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
    }

}

