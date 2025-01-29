package com.example.api.business.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessLocation is a Querydsl query type for BusinessLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessLocation extends EntityPathBase<BusinessLocation> {

    private static final long serialVersionUID = 1235580438L;

    public static final QBusinessLocation businessLocation = new QBusinessLocation("businessLocation");

    public final com.example.api.domain.QBaseEntity _super = new com.example.api.domain.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final StringPath zipcode = createString("zipcode");

    public QBusinessLocation(String variable) {
        super(BusinessLocation.class, forVariable(variable));
    }

    public QBusinessLocation(Path<? extends BusinessLocation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessLocation(PathMetadata metadata) {
        super(BusinessLocation.class, metadata);
    }

}

