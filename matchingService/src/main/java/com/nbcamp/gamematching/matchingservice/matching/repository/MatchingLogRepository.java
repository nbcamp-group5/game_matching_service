package com.nbcamp.gamematching.matchingservice.matching.repository;

import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingLogRepository extends JpaRepository<MatchingLog, Long> {

    MatchingLog findByResultMatchingAndMember(ResultMatching resultMatching, Member member);

    List<MatchingLog> findAllByMember(Member member);

    List<MatchingLog> findAllByResultMatching(ResultMatching resultMatching);
}
