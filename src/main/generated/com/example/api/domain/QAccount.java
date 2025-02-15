package com.example.api.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = -1087167288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath birthdate = createString("birthdate");

    public final StringPath callTime = createString("callTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath email = createString("email");

    public final BooleanPath emailReceivable = createBoolean("emailReceivable");

    public final StringPath introduction = createString("introduction");

    public final QLocation location;

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final EnumPath<com.example.api.account.entity.Nationality> nationality = createEnum("nationality", com.example.api.account.entity.Nationality.class);

    public final StringPath nickname = createString("nickname");

    public final BooleanPath openStatus = createBoolean("openStatus");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    public final CollectionPath<com.example.api.account.entity.UserRole, EnumPath<com.example.api.account.entity.UserRole>> roles = this.<com.example.api.account.entity.UserRole, EnumPath<com.example.api.account.entity.UserRole>>createCollection("roles", com.example.api.account.entity.UserRole.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath sex = createString("sex");

    public final NumberPath<Float> starPoint = createNumber("starPoint", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Integer> workCount = createNumber("workCount", Integer.class);

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
    }

}

