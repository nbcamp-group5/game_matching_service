package com.nbcamp.gamematching.matchingservice.board.repository.query;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardQueryRepository {

    Page<Board> findAllBoard(Pageable pageable);

    Page<Board> findMyBoard(Long memberId, Pageable pageable); //프로필 내가 쓴 글 조회

}
