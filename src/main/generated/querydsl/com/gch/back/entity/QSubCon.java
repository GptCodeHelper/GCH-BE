package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCon is a Querydsl query type for SubCon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubCon extends EntityPathBase<SubCon> {

    private static final long serialVersionUID = 1457942549L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCon subCon = new QSubCon("subCon");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final QSub sub;

    public final StringPath subConCode = createString("subConCode");

    public final NumberPath<Integer> subConId = createNumber("subConId", Integer.class);

    public final StringPath subConSort = createString("subConSort");

    public QSubCon(String variable) {
        this(SubCon.class, forVariable(variable), INITS);
    }

    public QSubCon(Path<? extends SubCon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCon(PathMetadata metadata, PathInits inits) {
        this(SubCon.class, metadata, inits);
    }

    public QSubCon(Class<? extends SubCon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sub = inits.isInitialized("sub") ? new QSub(forProperty("sub"), inits.get("sub")) : null;
    }

}

