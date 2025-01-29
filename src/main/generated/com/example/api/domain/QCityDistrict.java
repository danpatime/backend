package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCityDistrict is a Querydsl query type for CityDistrict
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCityDistrict extends EntityPathBase<CityDistrict> {

    private static final long serialVersionUID = 922969182L;

    public static final QCityDistrict cityDistrict = new QCityDistrict("cityDistrict");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath district = createString("district");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QCityDistrict(String variable) {
        super(CityDistrict.class, forVariable(variable));
    }

    public QCityDistrict(Path<? extends CityDistrict> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCityDistrict(PathMetadata metadata) {
        super(CityDistrict.class, metadata);
    }

}

