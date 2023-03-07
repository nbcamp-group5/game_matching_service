package com.nbcamp.gamematching.matchingservice.matching.repository;

import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ResultMatchingRepository extends JpaRepository<ResultMatching,Long> {
    Optional<ResultMatching> findFirstByDiscordUrl(String url);

}
