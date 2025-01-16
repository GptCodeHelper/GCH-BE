package com.gch.back.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 1516614213L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTest test = new QTest("test");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    //inherited
    public final StringPath chgId = _super.chgId;

    public final QProb prob;

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final NumberPath<Integer> testID = createNumber("testID", Integer.class);

    public final NumberPath<Integer> testInput = createNumber("testInput", Integer.class);

    public final NumberPath<Integer> testOutput = createNumber("testOutput", Integer.class);

    public QTest(String variable) {
        this(Test.class, forVariable(variable), INITS);
    }

    public QTest(Path<? extends Test> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTest(PathMetadata metadata, PathInits inits) {
        this(Test.class, metadata, inits);
    }

    public QTest(Class<? extends Test> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prob = inits.isInitialized("prob") ? new QProb(forProperty("prob")) : null;
    }

}

