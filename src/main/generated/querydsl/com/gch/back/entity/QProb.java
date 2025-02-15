package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProb is a Querydsl query type for Prob
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProb extends EntityPathBase<Prob> {

    private static final long serialVersionUID = 1516507400L;

    public static final QProb prob = new QProb("prob");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final ListPath<Lan, QLan> lanProbs = this.<Lan, QLan>createList("lanProbs", Lan.class, QLan.class, PathInits.DIRECT2);

    public final ListPath<ProbCon, QProbCon> probCons = this.<ProbCon, QProbCon>createList("probCons", ProbCon.class, QProbCon.class, PathInits.DIRECT2);

    public final NumberPath<Integer> probId = createNumber("probId", Integer.class);

    public final StringPath probTit = createString("probTit");

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public QProb(String variable) {
        super(Prob.class, forVariable(variable));
    }

    public QProb(Path<? extends Prob> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProb(PathMetadata metadata) {
        super(Prob.class, metadata);
    }

}

