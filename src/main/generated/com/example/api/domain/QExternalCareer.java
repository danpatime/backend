package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExternalCareer is a Querydsl query type for ExternalCareer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExternalCareer extends EntityPathBase<ExternalCareer> {

    private static final long serialVersionUID = 606757934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExternalCareer externalCareer = new QExternalCareer("externalCareer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QAccount employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Integer> workCount = createNumber("workCount", Integer.class);

    public QExternalCareer(String variable) {
        this(ExternalCareer.class, forVariable(variable), INITS);
    }

    public QExternalCareer(Path<? extends ExternalCareer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExternalCareer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExternalCareer(PathMetadata metadata, PathInits inits) {
        this(ExternalCareer.class, metadata, inits);
    }

    public QExternalCareer(Class<? extends ExternalCareer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

