package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.CommentRepository;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
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
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    //게시글 작성
    public void createBoard(CreateBoardRequest createBoardRequest,Member member) {
        Board board = new Board(member.getNickname(), createBoardRequest.getBoardImageUrl(), createBoardRequest.getContent(),member);
        boardRepository.save(board);
    }

    //게시글 조회 -  페이지값을 입력할 때 게시글과 댓글페이지가 1 2 3 같이 이동?...
    public List<BoardResponse> getBoardList() {
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(1));
        List<BoardResponse> boardResponseList = new ArrayList<>();
        for (Board board : boardPage) {
            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(), pageableSetting(1));
            List<CommentResponse> commentList = new ArrayList<>();
            for (Comment comment : commentPage) {
                commentList.add(new CommentResponse(comment));
            }
            Long likeCount = likeRepository.countByBoardId(board.getId());
            boardResponseList.add(new BoardResponse(board, commentList, likeCount));
        }
        return boardResponseList;
    }

    //게시글 수정
    public void updateBoard(Long boardId, UpdateBoardRequest boardRequest,Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
        board.checkUser(board,member);
        board.updateBoard(boardRequest,member);
        boardRepository.save(board);
    }

    //게시글 삭제
    public void deleteBoard(Long boardId,Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        board.checkUser(board,member);
        boardRepository.deleteById(boardId);
    }

    //페이징
    public Pageable pageableSetting(int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return pageable;
    }
}

