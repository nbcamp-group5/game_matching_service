package com.nbcamp.gamematching.matchingservice.member.repository.query;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nbcamp.gamematching.matchingservice.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        //이 메서드로는 board나 comment를 가져오는 로직이 필요 없는 것 같아서 join 안 함
        return Optional.ofNullable(jpaQueryFactory
                .select(member)
                .from(member)
                .where(
                        member.email.eq(email)
                )
                .fetchOne());
    }

    @Override //이 메서드는 jpa를 그냥 쓰는 거랑 다를 바 없다고 보는데, , 일단 추가함
    public boolean existsMemberByEmail(String email) {
        Integer existence = jpaQueryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst();

        return existence != null;
    }

    @Override
    public Optional<Member> findMemberByNickname(String friendNick) {
        //이 메서드로는 board나 comment를 가져오는 로직이 필요 없는 것 같아서 join 안 함
        return Optional.ofNullable(jpaQueryFactory
                .select(member)
                .from(member)
                .where(
                        member.profile.nickname.eq(friendNick)
                )
                .fetchOne());
    }
}
