package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -2067215913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> contractEndTime = createDateTime("contractEndTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> contractHourlyPay = createNumber("contractHourlyPay", Integer.class);

    public final NumberPath<Long> contractId = createNumber("contractId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> contractStartTime = createDateTime("contractStartTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QOfferEmployment offerEmployment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.offerEmployment = inits.isInitialized("offerEmployment") ? new QOfferEmployment(forProperty("offerEmployment"), inits.get("offerEmployment")) : null;
    }

}

