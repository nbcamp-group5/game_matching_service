package com.nbcamp.gamematching.matchingservice.matching.repository;

import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import com.nbcamp.gamematching.matchingservice.matching.repository.Query.MatchingQueryRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingLogRepository extends JpaRepository<MatchingLog, Long> , MatchingQueryRepository {

    MatchingLog findByResultMatchingAndMember(ResultMatching resultMatching, Member member);

    List<MatchingLog> findAllByMember(Member member);

    List<MatchingLog> findAllByResultMatching(ResultMatching resultMatching);
}
