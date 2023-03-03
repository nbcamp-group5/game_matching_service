package com.nbcamp.gamematching.matchingservice.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1898948020L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity _super = new com.nbcamp.gamematching.matchingservice.common.entity.QBaseEntity(this);

    public final StringPath boardImage = createString("boardImage");

    public final ListPath<com.nbcamp.gamematching.matchingservice.comment.entity.Comment, com.nbcamp.gamematching.matchingservice.comment.entity.QComment> comments = this.<com.nbcamp.gamematching.matchingservice.comment.entity.Comment, com.nbcamp.gamematching.matchingservice.comment.entity.QComment>createList("comments", com.nbcamp.gamematching.matchingservice.comment.entity.Comment.class, com.nbcamp.gamematching.matchingservice.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nbcamp.gamematching.matchingservice.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.nbcamp.gamematching.matchingservice.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

