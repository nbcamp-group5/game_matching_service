package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLike;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousLikeRepository extends JpaRepository<AnonymousLike,Long> {

    Long countByAnonymousBoardId(Long boardId);


    void deleteAllByAnonymousBoardId(Long boardId);

    boolean existsByMemberAndAnonymousBoard(Member member, AnonymousBoard board);

    void deleteByMemberAndAnonymousBoard(Member member, AnonymousBoard board);
}
