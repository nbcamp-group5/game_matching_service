package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnonymousBoardService {

    void createAnonymousBoard(CreateBoardRequest createBoardRequest, Member member,
                              MultipartFile image);

    List<AnonymousBoardResponse> getAnonymousBoardList();

    void updateAnonymousBoard(Long boardId, UpdateBoardRequest boardRequest, Member member,
                              MultipartFile image);

    void deleteAnonymousBoard(Long boardId, Member member);

    AnonymousBoardResponse getAnonymousBoard(Long boardId);

    List<AnonymousBoardAdminDto> getAnonymousBoardsByAdmin(Integer page);

    void deleteAnonymousBoardByAdmin(Long boardId);

    AnonymousBoard findBoard(Long boardId);
}
