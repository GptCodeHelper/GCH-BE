package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLan is a Querydsl query type for Lan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLan extends EntityPathBase<Lan> {

    private static final long serialVersionUID = -366726778L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLan lan = new QLan("lan");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final NumberPath<Integer> lanId = createNumber("lanId", Integer.class);

    public final StringPath lanNM = createString("lanNM");

    public final QProb prob;

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public QLan(String variable) {
        this(Lan.class, forVariable(variable), INITS);
    }

    public QLan(Path<? extends Lan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLan(PathMetadata metadata, PathInits inits) {
        this(Lan.class, metadata, inits);
    }

    public QLan(Class<? extends Lan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prob = inits.isInitialized("prob") ? new QProb(forProperty("prob")) : null;
    }

}

