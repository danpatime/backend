package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPossibleBoard is a Querydsl query type for PossibleBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPossibleBoard extends EntityPathBase<PossibleBoard> {

    private static final long serialVersionUID = -1378693552L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPossibleBoard possibleBoard = new QPossibleBoard("possibleBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QAccount employee;

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> possibleId = createNumber("possibleId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QPossibleBoard(String variable) {
        this(PossibleBoard.class, forVariable(variable), INITS);
    }

    public QPossibleBoard(Path<? extends PossibleBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPossibleBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPossibleBoard(PathMetadata metadata, PathInits inits) {
        this(PossibleBoard.class, metadata, inits);
    }

    public QPossibleBoard(Class<? extends PossibleBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

