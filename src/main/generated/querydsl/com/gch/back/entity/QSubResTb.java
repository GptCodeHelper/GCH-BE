package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubResTb is a Querydsl query type for SubResTb
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubResTb extends EntityPathBase<SubResTb> {

    private static final long serialVersionUID = 937013505L;

    public static final QSubResTb subResTb = new QSubResTb("subResTb");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final NumberPath<Integer> resId = createNumber("resId", Integer.class);

    public final StringPath subMes = createString("subMes");

    public QSubResTb(String variable) {
        super(SubResTb.class, forVariable(variable));
    }

    public QSubResTb(Path<? extends SubResTb> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubResTb(PathMetadata metadata) {
        super(SubResTb.class, metadata);
    }

}

