package com.nbcamp.gamematching.matchingservice.matching.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchingLog is a Querydsl query type for MatchingLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchingLog extends EntityPathBase<MatchingLog> {

    private static final long serialVersionUID = 1366297796L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchingLog matchingLog = new QMatchingLog("matchingLog");

    public final BooleanPath evaluation = createBoolean("evaluation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nbcamp.gamematching.matchingservice.member.entity.QMember member;

    public final QResultMatching resultMatching;

    public QMatchingLog(String variable) {
        this(MatchingLog.class, forVariable(variable), INITS);
    }

    public QMatchingLog(Path<? extends MatchingLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchingLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchingLog(PathMetadata metadata, PathInits inits) {
        this(MatchingLog.class, metadata, inits);
    }

    public QMatchingLog(Class<? extends MatchingLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.nbcamp.gamematching.matchingservice.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.resultMatching = inits.isInitialized("resultMatching") ? new QResultMatching(forProperty("resultMatching")) : null;
    }

}

