package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProbCon is a Querydsl query type for ProbCon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProbCon extends EntityPathBase<ProbCon> {

    private static final long serialVersionUID = -488965286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProbCon probCon = new QProbCon("probCon");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final QProb prob;

    public final NumberPath<Integer> probConId = createNumber("probConId", Integer.class);

    public final StringPath probConSort = createString("probConSort");

    public final StringPath probCont = createString("probCont");

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public QProbCon(String variable) {
        this(ProbCon.class, forVariable(variable), INITS);
    }

    public QProbCon(Path<? extends ProbCon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProbCon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProbCon(PathMetadata metadata, PathInits inits) {
        this(ProbCon.class, metadata, inits);
    }

    public QProbCon(Class<? extends ProbCon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prob = inits.isInitialized("prob") ? new QProb(forProperty("prob")) : null;
    }

}

