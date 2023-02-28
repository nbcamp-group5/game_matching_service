package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.BoardAdminDto;
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
import com.nbcamp.gamematching.matchingservice.member.domain.FileStore;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    private final FileStore fileStore;

    //게시글 작성
    public void createBoard(CreateBoardRequest createBoardRequest, Member member,
            MultipartFile image) throws IOException {
        String imageFile = fileStore.storeFile(image);
        Board board = new Board(member.getProfile().getNickname(), imageFile,
                createBoardRequest.getContent(), member);
        boardRepository.save(board);
    }

    //게시글 조회 -  페이지값을 입력할 때 게시글과 댓글페이지가 1 2 3 같이 이동?...
    public List<BoardResponse> getBoardList() {
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(1));
        List<BoardResponse> boardResponseList = new ArrayList<>();
        for (Board board : boardPage) {
            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(),
                    pageableSetting(1));
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
    public void updateBoard(Long boardId, UpdateBoardRequest boardRequest, Member member,
            MultipartFile image) throws IOException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
        board.checkUser(board, member);
        String imageFile = fileStore.storeFile(image);
        board.updateBoard(boardRequest, imageFile, member);
        boardRepository.save(board);
    }

    //게시글 삭제
    public void deleteBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
        board.checkUser(board, member);
        likeRepository.deleteAllByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }

    //페이징
    public static Pageable pageableSetting(int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page - 1, 5, sort);
        return pageable;
    }

//    //게시글 검색
//    public List<BoardResponse> getBoardList1(String searchName) {
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        QBoard qboard = QBoard.board;
//        booleanBuilder.and(qboard.content.contains(searchName));
//        booleanBuilder.or(qboard.member.profile.nickname.contains(searchName));
//        Page<Board> boardPage = boardRepository.findAll(booleanBuilder, pageableSetting(1));
//        List<BoardResponse> boardResponseList = new ArrayList<>();
//        for (Board board : boardPage) {
//            Page<Comment> commentPage = commentRepository.findAllByBoardId(board.getId(), pageableSetting(1));
//            List<CommentResponse> commentList = new ArrayList<>();
//            for (Comment comment : commentPage) {
//                commentList.add(new CommentResponse(comment));
//            }
//            Long likeCount = likeRepository.countByBoardId(board.getId());
//            boardResponseList.add(new BoardResponse(board, commentList, likeCount));
//        }
//        return boardResponseList;
//    }

    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        BoardResponse boardResponse = new BoardResponse(board);
        return boardResponse;
    }

    public List<BoardAdminDto> getBoardsByAdmin(Integer page) {
        Page<Board> boardPage = boardRepository.findAll(pageableSetting(page));
        return BoardAdminDto.of(boardPage.getContent());
    }

    public void deleteBoardByAdmin(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}