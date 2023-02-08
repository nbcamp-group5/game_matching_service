package com.nbcamp.gamematching.matchingservice.board.repository;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousBoardRepository extends JpaRepository<AnonymousBoard,Long> {

//    List<Board> findAllByOrderByModDateDesc();
}
