package com.nbcamp.gamematching.matchingservice.board.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModifiedAtDesc();

    Page<Board> findAllByMemberId(Pageable pageable, Long memberId);
}
