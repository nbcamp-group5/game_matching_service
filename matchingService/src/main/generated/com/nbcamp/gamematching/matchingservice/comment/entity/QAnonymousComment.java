package com.nbcamp.gamematching.matchingservice.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnonymousComment is a Querydsl query type for AnonymousComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnonymousComment extends EntityPathBase<AnonymousComment> {

    private static final long serialVersionUID = 664649925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnonymousComment anonymousComment = new QAnonymousComment("anonymousComment");

    public final com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity _super = new com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity(this);

    public final com.nbcamp.gamematching.matchingservice.board.entity.QAnonymousBoard anonymousBoard;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nbcamp.gamematching.matchingservice.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public QAnonymousComment(String variable) {
        this(AnonymousComment.class, forVariable(variable), INITS);
    }

    public QAnonymousComment(Path<? extends AnonymousComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnonymousComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnonymousComment(PathMetadata metadata, PathInits inits) {
        this(AnonymousComment.class, metadata, inits);
    }

    public QAnonymousComment(Class<? extends AnonymousComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.anonymousBoard = inits.isInitialized("anonymousBoard") ? new com.nbcamp.gamematching.matchingservice.board.entity.QAnonymousBoard(forProperty("anonymousBoard"), inits.get("anonymousBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.nbcamp.gamematching.matchingservice.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

