package com.nbcamp.gamematching.matchingservice.matching.repository;

import com.nbcamp.gamematching.matchingservice.matching.entity.ResponseMatching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseMatchingRepository extends JpaRepository<ResponseMatching,Long> {
}
