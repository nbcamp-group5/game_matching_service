package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Long countByBoardId(Long boardId);
}
