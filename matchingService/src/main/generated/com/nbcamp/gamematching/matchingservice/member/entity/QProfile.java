package com.nbcamp.gamematching.matchingservice.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProfile extends BeanPath<Profile> {

    private static final long serialVersionUID = -678496151L;

    public static final QProfile profile = new QProfile("profile");

    public final EnumPath<com.nbcamp.gamematching.matchingservice.member.domain.GameType> game = createEnum("game", com.nbcamp.gamematching.matchingservice.member.domain.GameType.class);

    public final NumberPath<Integer> mannerPoints = createNumber("mannerPoints", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath profileImage = createString("profileImage");

    public final EnumPath<com.nbcamp.gamematching.matchingservice.member.domain.Tier> tier = createEnum("tier", com.nbcamp.gamematching.matchingservice.member.domain.Tier.class);

    public QProfile(String variable) {
        super(Profile.class, forVariable(variable));
    }

    public QProfile(Path<? extends Profile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfile(PathMetadata metadata) {
        super(Profile.class, metadata);
    }

}

