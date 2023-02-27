package com.nbcamp.gamematching.matchingservice.like.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Long countByBoardId(Long boardId);

    void deleteAllByBoardId(Long boardId);

    void deleteByMemberAndBoard(Member member, Board board);

    boolean existsByMemberAndBoard(Member member, Board board);
}
