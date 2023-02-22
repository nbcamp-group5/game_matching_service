package com.nbcamp.gamematching.matchingservice.board.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor {

    Page<Board> findAllByMemberId(Long memberId, Pageable pageable);


}
