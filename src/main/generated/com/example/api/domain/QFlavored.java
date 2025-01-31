package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlavored is a Querydsl query type for Flavored
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlavored extends EntityPathBase<Flavored> {

    private static final long serialVersionUID = 128299138L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlavored flavored = new QFlavored("flavored");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QAccount employee;

    public final NumberPath<Long> flavoredId = createNumber("flavoredId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QFlavored(String variable) {
        this(Flavored.class, forVariable(variable), INITS);
    }

    public QFlavored(Path<? extends Flavored> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlavored(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlavored(PathMetadata metadata, PathInits inits) {
        this(Flavored.class, metadata, inits);
    }

    public QFlavored(Class<? extends Flavored> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

