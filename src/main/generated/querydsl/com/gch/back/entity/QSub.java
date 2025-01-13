package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSub is a Querydsl query type for Sub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSub extends EntityPathBase<Sub> {

    private static final long serialVersionUID = -366719443L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSub sub = new QSub("sub");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final QProb prob;

    public final NumberPath<Integer> probConId = createNumber("probConId", Integer.class);

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final QUser user;

    public QSub(String variable) {
        this(Sub.class, forVariable(variable), INITS);
    }

    public QSub(Path<? extends Sub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSub(PathMetadata metadata, PathInits inits) {
        this(Sub.class, metadata, inits);
    }

    public QSub(Class<? extends Sub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prob = inits.isInitialized("prob") ? new QProb(forProperty("prob")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

