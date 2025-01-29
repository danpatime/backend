package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlavoredCategory is a Querydsl query type for FlavoredCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFlavoredCategory extends EntityPathBase<FlavoredCategory> {

    private static final long serialVersionUID = -258789472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlavoredCategory flavoredCategory = new QFlavoredCategory("flavoredCategory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QAccount employee;

    public final NumberPath<Long> flavoredCategoryId = createNumber("flavoredCategoryId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QFlavoredCategory(String variable) {
        this(FlavoredCategory.class, forVariable(variable), INITS);
    }

    public QFlavoredCategory(Path<? extends FlavoredCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFlavoredCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFlavoredCategory(PathMetadata metadata, PathInits inits) {
        this(FlavoredCategory.class, metadata, inits);
    }

    public QFlavoredCategory(Class<? extends FlavoredCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

