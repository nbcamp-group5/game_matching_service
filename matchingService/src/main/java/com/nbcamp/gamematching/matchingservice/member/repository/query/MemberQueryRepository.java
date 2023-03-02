package com.nbcamp.gamematching.matchingservice.member.repository.query;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;

import java.util.Optional;

public interface MemberQueryRepository {

    Optional<Member> findMemberByEmail(String email);
    boolean existsMemberByEmail(String email);
    Optional<Member> findMemberByNickname(String friendNick);
}
