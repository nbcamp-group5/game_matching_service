package com.nbcamp.gamematching.matchingservice.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnonymousBoard is a Querydsl query type for AnonymousBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnonymousBoard extends EntityPathBase<AnonymousBoard> {

    private static final long serialVersionUID = -442610221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnonymousBoard anonymousBoard = new QAnonymousBoard("anonymousBoard");

    public final com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity _super = new com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity(this);

    public final StringPath boardImage = createString("boardImage");

    public final ListPath<com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment, com.nbcamp.gamematching.matchingservice.comment.entity.QAnonymousComment> comments = this.<com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment, com.nbcamp.gamematching.matchingservice.comment.entity.QAnonymousComment>createList("comments", com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment.class, com.nbcamp.gamematching.matchingservice.comment.entity.QAnonymousComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nbcamp.gamematching.matchingservice.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public QAnonymousBoard(String variable) {
        this(AnonymousBoard.class, forVariable(variable), INITS);
    }

    public QAnonymousBoard(Path<? extends AnonymousBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnonymousBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnonymousBoard(PathMetadata metadata, PathInits inits) {
        this(AnonymousBoard.class, metadata, inits);
    }

    public QAnonymousBoard(Class<? extends AnonymousBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.nbcamp.gamematching.matchingservice.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

