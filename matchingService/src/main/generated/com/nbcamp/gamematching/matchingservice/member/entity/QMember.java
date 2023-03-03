package com.nbcamp.gamematching.matchingservice.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -258391078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath email = createString("email");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final ListPath<com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog, com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog> matchingLogs = this.<com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog, com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog>createList("matchingLogs", com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog.class, com.nbcamp.gamematching.matchingservice.matching.entity.QMatchingLog.class, PathInits.DIRECT2);

    public final ListPath<Member, QMember> myBuddies = this.<Member, QMember>createList("myBuddies", Member.class, QMember.class, PathInits.DIRECT2);

    public final ListPath<Member, QMember> notYetBuddies = this.<Member, QMember>createList("notYetBuddies", Member.class, QMember.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final QProfile profile;

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    public final EnumPath<com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum> role = createEnum("role", com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

