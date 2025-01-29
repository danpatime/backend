package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlavoredDistrict is a Querydsl query type for FlavoredDistrict
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlavoredDistrict extends EntityPathBase<FlavoredDistrict> {

    private static final long serialVersionUID = -20339152L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlavoredDistrict flavoredDistrict = new QFlavoredDistrict("flavoredDistrict");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QCityDistrict district;

    public final QAccount employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QFlavoredDistrict(String variable) {
        this(FlavoredDistrict.class, forVariable(variable), INITS);
    }

    public QFlavoredDistrict(Path<? extends FlavoredDistrict> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlavoredDistrict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlavoredDistrict(PathMetadata metadata, PathInits inits) {
        this(FlavoredDistrict.class, metadata, inits);
    }

    public QFlavoredDistrict(Class<? extends FlavoredDistrict> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.district = inits.isInitialized("district") ? new QCityDistrict(forProperty("district")) : null;
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

