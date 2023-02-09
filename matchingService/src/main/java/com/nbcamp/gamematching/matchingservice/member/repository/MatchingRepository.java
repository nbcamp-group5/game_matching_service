package com.nbcamp.gamematching.matchingservice.member.repository;

import com.nbcamp.gamematching.matchingservice.member.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Long> {

}
