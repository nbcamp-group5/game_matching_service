package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.repository.AnonymousCommentRepository;
import com.nbcamp.gamematching.matchingservice.common.domain.CreatePageable;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.like.repository.AnonymousLikeRepository;
import com.nbcamp.gamematching.matchingservice.member.domain.FileDetail;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnonymousBoardServiceImpl implements AnonymousBoardService{

    private final AnonymousBoardRepository anonymousBoardRepository;
    private final AnonymousLikeRepository anonymousLikeRepository;
    private final FileUploadService fileUploadService;


    //익명 게시글 작성
    public void createAnonymousBoard(CreateBoardRequest createBoardRequest, Member member,
            MultipartFile image) {
        if(image == null) {
            String boardImage = "images/nav/logo.png";
            AnonymousBoard board = new AnonymousBoard(boardImage,
                    createBoardRequest.getContent(), member);
            anonymousBoardRepository.save(board);

        } else {
            FileDetail fileDetail = fileUploadService.save(image);
            AnonymousBoard board = new AnonymousBoard(fileDetail.getPath(),
                    createBoardRequest.getContent(), member);
            anonymousBoardRepository.save(board);
            System.out.println(fileDetail.getPath());
        }
    }

    //익명 게시글 조회
    public List<AnonymousBoardResponse> getAnonymousBoardList() {
        Page<AnonymousBoard> boardPage = anonymousBoardRepository.findAll(pageableSetting(1));
        List<AnonymousBoardResponse> boardResponseList = new ArrayList<>();
        for (AnonymousBoard board : boardPage) {
            Long likeCount = anonymousLikeRepository.countByAnonymousBoardId(board.getId());
            boardResponseList.add(new AnonymousBoardResponse(board, likeCount));
        }
        return boardResponseList;
    }

    //익명 게시글 수정
    public void updateAnonymousBoard(Long boardId, UpdateBoardRequest boardRequest, Member member,
            MultipartFile image) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId)
                .orElseThrow(NotFoundException::new);
        board.checkUser(board, member);
        if(image == null) {
            String boardImage = "images/5a169b5b-09ba-4e42-8ee0-9850f3a0c483.jpeg";
            board.updateAnonymousBoard(boardRequest, boardImage, member);
            anonymousBoardRepository.save(board);
        } else {
            FileDetail fileDetail = fileUploadService.save(image);
            board.updateAnonymousBoard(boardRequest, fileDetail.getPath(), member);
            anonymousBoardRepository.save(board);
        }
    }

    //익명 게시글 삭제
    public void deleteAnonymousBoard(Long boardId, Member member) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId)
                .orElseThrow(NotFoundException::new);
        board.checkUser(board, member);
        anonymousLikeRepository.deleteAllByAnonymousBoardId(boardId);
        anonymousBoardRepository.deleteById(boardId);
    }

    //페이징
    public Pageable pageableSetting(int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return pageable;
    }

    public AnonymousBoardResponse getAnonymousBoard(Long boardId) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId)
                .orElseThrow(NotFoundException::new);
        AnonymousBoardResponse anonymousBoardResponse = new AnonymousBoardResponse(board);
        return anonymousBoardResponse;
    }

    public List<AnonymousBoardAdminDto> getAnonymousBoardsByAdmin(Integer page) {
        Page<AnonymousBoard> boardPage = anonymousBoardRepository.findAll(
                CreatePageable.createPageable(page));
        return AnonymousBoardAdminDto.of(boardPage.getContent());
    }

    public void deleteAnonymousBoardByAdmin(Long boardId) {
        anonymousBoardRepository.deleteById(boardId);
    }

    public AnonymousBoard findBoard(Long boardId) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(NotFoundException::new);
        return board;
    }
}
