package com.nbcamp.gamematching.matchingservice.matching.repository;

import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingLogRepository extends JpaRepository<MatchingLog,Long> {
}
