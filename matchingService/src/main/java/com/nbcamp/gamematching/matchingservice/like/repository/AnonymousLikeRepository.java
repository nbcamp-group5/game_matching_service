package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousLikeRepository extends JpaRepository<AnonymousLike,Long> {

    Long countByAnonymousBoardId(Long boardId);

}
