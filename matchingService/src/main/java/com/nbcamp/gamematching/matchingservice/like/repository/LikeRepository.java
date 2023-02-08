package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {

    Long countByBoardId(Long boardId);
}
