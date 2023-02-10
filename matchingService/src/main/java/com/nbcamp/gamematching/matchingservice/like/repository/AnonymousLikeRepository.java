package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousLikeRepository extends JpaRepository<AnonymousLikes,Long> {

    Long countByAnonymousBoardId(Long boardId);

}
