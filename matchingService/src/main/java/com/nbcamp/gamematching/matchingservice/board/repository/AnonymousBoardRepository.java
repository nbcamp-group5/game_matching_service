package com.nbcamp.gamematching.matchingservice.board.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnonymousBoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModDateDesc();
}
