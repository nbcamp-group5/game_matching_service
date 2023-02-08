package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.CommentRepository;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AnonymousBoardRepository anonymousBoardRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    //게시글 작성
    public void createBoard(CreateBoardRequest createBoardRequest) {
        Board board = new Board(createBoardRequest.getBoardImageUrl(), createBoardRequest.getContent());
        boardRepository.save(board);
    }

    //익명 게시글 작성
    public void createAnonymousBoard(CreateBoardRequest createBoardRequest) {
        Board board = new Board(createBoardRequest.getNickname(), createBoardRequest.getBoardImageUrl(), createBoardRequest.getContent());
        anonymousBoardRepository.save(board);
    }

    //게시글 조회
    public List<BoardResponse> getBoardList() {
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(1));
        List<BoardResponse> boardResponseList = new ArrayList<>();
        for (Board board : boardPage) {
            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(), pageableSetting(1));
            List<CommentResponse> commentList = new ArrayList<>();
            for (Comment comment : commentPage) {
                commentList.add(new CommentResponse(comment));
                Long likeCount = likeRepository.countByBoardId(board.getId());
                boardResponseList.add(new BoardResponse(board, commentList, likeCount));
            }
        }
        return boardResponseList;
    }

    //익명 게시글 조회
    public List<BoardResponse> getAnonymousBoardList() {
        Page<Board> boardPage = anonymousBoardRepository.findAll(pageableSetting(1));
        List<BoardResponse> boardResponseList = new ArrayList<>();
        for (Board board : boardPage) {
            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(), pageableSetting(1));
            List<CommentResponse> commentList = new ArrayList<>();
            for (Comment comment : commentPage) {
                commentList.add(new CommentResponse(comment));
                Long likeCount = likeRepository.countByBoardId(board.getId());
                boardResponseList.add(new BoardResponse(board, commentList, likeCount));
            }
        }
        return boardResponseList;
    }

    //게시글 수정
    public void updateBoard(Long boardId, UpdateBoardRequest boardRequest) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException());
        //게시글 작성한 사람이 맞는지 확인하기
        board.updateBoard(boardRequest);
        boardRepository.save(board);
    }

    //익명 게시글 수정
    public void updateAnonymousBoard(Long boardId, UpdateBoardRequest boardRequest) {
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException());
        //게시글 작성한 사람이 맞는지 확인하기
        board.updateBoard(boardRequest);
        anonymousBoardRepository.save(board);
    }

    //게시글 삭제
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        //게시글 작성한 사람이 맞는지 확인하기
        boardRepository.deleteById(boardId);
    }

    //익명 게시글 삭제
    public void deleteAnonymousBoard(Long boardId) {
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        //게시글 작성한 사람이 맞는지 확인하기
        anonymousBoardRepository.deleteById(boardId);
    }

    //페이징
    public Pageable pageableSetting(int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modDate");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return pageable;
    }
}

