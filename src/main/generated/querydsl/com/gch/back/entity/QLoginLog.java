package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginLog is a Querydsl query type for LoginLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoginLog extends EntityPathBase<LoginLog> {

    private static final long serialVersionUID = 756806478L;

    public static final QLoginLog loginLog = new QLoginLog("loginLog");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath agInf = createString("agInf");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final StringPath loginId = createString("loginId");

    public final StringPath logSeq = createString("logSeq");

    public final StringPath logType = createString("logType");

    public final DateTimePath<java.time.LocalDateTime> logYmd = createDateTime("logYmd", java.time.LocalDateTime.class);

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public QLoginLog(String variable) {
        super(LoginLog.class, forVariable(variable));
    }

    public QLoginLog(Path<? extends LoginLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginLog(PathMetadata metadata) {
        super(LoginLog.class, metadata);
    }

}

