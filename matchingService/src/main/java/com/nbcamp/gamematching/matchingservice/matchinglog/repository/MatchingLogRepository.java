package com.nbcamp.gamematching.matchingservice.matchinglog.repository;

import com.nbcamp.gamematching.matchingservice.matchinglog.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingLogRepository extends JpaRepository<MatchingLog, Long> {

    Page<MatchingLog> findAllByMember(Member member);

}
