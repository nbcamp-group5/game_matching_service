package com.nbcamp.gamematching.matchingservice.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnonymousLike is a Querydsl query type for AnonymousLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnonymousLike extends EntityPathBase<AnonymousLike> {

    private static final long serialVersionUID = 481922375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnonymousLike anonymousLike = new QAnonymousLike("anonymousLike");

    public final com.nbcamp.gamematching.matchingservice.board.entity.QAnonymousBoard anonymousBoard;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nbcamp.gamematching.matchingservice.member.entity.QMember member;

    public QAnonymousLike(String variable) {
        this(AnonymousLike.class, forVariable(variable), INITS);
    }

    public QAnonymousLike(Path<? extends AnonymousLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnonymousLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnonymousLike(PathMetadata metadata, PathInits inits) {
        this(AnonymousLike.class, metadata, inits);
    }

    public QAnonymousLike(Class<? extends AnonymousLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.anonymousBoard = inits.isInitialized("anonymousBoard") ? new com.nbcamp.gamematching.matchingservice.board.entity.QAnonymousBoard(forProperty("anonymousBoard"), inits.get("anonymousBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.nbcamp.gamematching.matchingservice.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

