package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOfferEmployment is a Querydsl query type for OfferEmployment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOfferEmployment extends EntityPathBase<OfferEmployment> {

    private static final long serialVersionUID = -82696445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOfferEmployment offerEmployment = new QOfferEmployment("offerEmployment");

    public final QBusiness business;

    public final QContract contract;

    public final QAccount employee;

    public final DateTimePath<java.time.LocalDateTime> suggestEndTime = createDateTime("suggestEndTime", java.time.LocalDateTime.class);

    public final BooleanPath suggestFinished = createBoolean("suggestFinished");

    public final NumberPath<Integer> suggestHourlyPay = createNumber("suggestHourlyPay", Integer.class);

    public final NumberPath<Long> suggestId = createNumber("suggestId", Long.class);

    public final BooleanPath suggestReaded = createBoolean("suggestReaded");

    public final DateTimePath<java.time.LocalDateTime> suggestRegisterTime = createDateTime("suggestRegisterTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> suggestStartTime = createDateTime("suggestStartTime", java.time.LocalDateTime.class);

    public final BooleanPath suggestSucceeded = createBoolean("suggestSucceeded");

    public QOfferEmployment(String variable) {
        this(OfferEmployment.class, forVariable(variable), INITS);
    }

    public QOfferEmployment(Path<? extends OfferEmployment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOfferEmployment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOfferEmployment(PathMetadata metadata, PathInits inits) {
        this(OfferEmployment.class, metadata, inits);
    }

    public QOfferEmployment(Class<? extends OfferEmployment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.business = inits.isInitialized("business") ? new QBusiness(forProperty("business"), inits.get("business")) : null;
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
        this.employee = inits.isInitialized("employee") ? new QAccount(forProperty("employee")) : null;
    }

}

