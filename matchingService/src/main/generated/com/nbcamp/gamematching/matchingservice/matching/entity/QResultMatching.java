package com.nbcamp.gamematching.matchingservice.matching.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResultMatching is a Querydsl query type for ResultMatching
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResultMatching extends EntityPathBase<ResultMatching> {

    private static final long serialVersionUID = 1684450141L;

    public static final QResultMatching resultMatching = new QResultMatching("resultMatching");

    public final StringPath discordUrl = createString("discordUrl");

    public final StringPath gameInfo = createString("gameInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath playMode = createString("playMode");

    public QResultMatching(String variable) {
        super(ResultMatching.class, forVariable(variable));
    }

    public QResultMatching(Path<? extends ResultMatching> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResultMatching(PathMetadata metadata) {
        super(ResultMatching.class, metadata);
    }

}

