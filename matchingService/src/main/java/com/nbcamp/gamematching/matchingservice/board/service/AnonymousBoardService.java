package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import com.nbcamp.gamematching.matchingservice.comment.repository.AnonymousCommentRepository;
import com.nbcamp.gamematching.matchingservice.like.repository.AnonymousLikeRepository;
import com.nbcamp.gamematching.matchingservice.member.domain.FileStore;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnonymousBoardService {

    private final AnonymousBoardRepository anonymousBoardRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;
    private final AnonymousLikeRepository anonymousLikeRepository;

    private final FileStore fileStore;


    //익명 게시글 작성
    public void createAnonymousBoard(CreateBoardRequest createBoardRequest, Member member, MultipartFile image) throws IOException {
        String imageFile = fileStore.storeFile(image);
        AnonymousBoard board = new AnonymousBoard(AnonymousBoard.nNick(), imageFile, createBoardRequest.getContent(),member);
        anonymousBoardRepository.save(board);
    }

    //익명 게시글 조회
    public List<AnonymousBoardResponse> getAnonymousBoardList() {
        Page<AnonymousBoard> boardPage = anonymousBoardRepository.findAll(pageableSetting(1));
        List<AnonymousBoardResponse> boardResponseList = new ArrayList<>();
        for (AnonymousBoard board : boardPage) {
            Page<AnonymousComment> commentPage = anonymousCommentRepository.findAllByAnonymousBoardId(board.getId(), pageableSetting(1));
            List<AnonymousCommentResponse> commentList = new ArrayList<>();
            for (AnonymousComment comment : commentPage) {
                commentList.add(new AnonymousCommentResponse(comment));
            }
            Long likeCount = anonymousLikeRepository.countByAnonymousBoardId(board.getId());
            boardResponseList.add(new AnonymousBoardResponse(board, commentList, likeCount));
        }
        return boardResponseList;
    }

    //익명 게시글 수정
    public void updateAnonymousBoard(Long boardId, UpdateBoardRequest boardRequest, Member member, MultipartFile image) throws IOException {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException());
        board.checkUser(board,member);
        String imageFile = fileStore.storeFile(image);
        board.updateAnonymousBoard(boardRequest,imageFile,member);
        anonymousBoardRepository.save(board);
    }

    //익명 게시글 삭제
    public void deleteAnonymousBoard(Long boardId,Member member) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        board.checkUser(board,member);
        anonymousBoardRepository.deleteById(boardId);
    }

    //페이징
    public Pageable pageableSetting(int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return pageable;
    }
}
