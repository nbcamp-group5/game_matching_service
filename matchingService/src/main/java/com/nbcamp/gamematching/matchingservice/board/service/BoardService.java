package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.BoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    void createBoard(CreateBoardRequest createBoardRequest, Member member,
                     MultipartFile image);

    List<BoardResponse> getBoardList();

    void updateBoard(Long boardId, UpdateBoardRequest boardRequest, Member member,
                     MultipartFile image);

    void deleteBoard(Long boardId, Member member);

    BoardResponse getBoard(Long boardId);

    List<BoardAdminDto> getBoardsByAdmin(Integer page);

    void deleteBoardByAdmin(Long boardId);

    Page<Board> findAllByMemberId(Long memberId, Pageable pageable);

    Board findBoard(Long boardId);
}
