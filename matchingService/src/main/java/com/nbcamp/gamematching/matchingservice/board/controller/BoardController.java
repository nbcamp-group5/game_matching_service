package com.nbcamp.gamematching.matchingservice.board.controller;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.service.AnonymousBoardService;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final AnonymousBoardService anonymousBoardService;

    //게시글 작성
    @PostMapping("/normal")
    public ResponseEntity<String> createBoard(@RequestBody CreateBoardRequest createBoardRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.createBoard(createBoardRequest, userDetails.getUser());
        return new ResponseEntity<>("게시글 작성완료", HttpStatus.CREATED);
    }

    //익명 게시글 작성
    @PostMapping("/anonymous")
    public ResponseEntity<String> createAnonymousBoard(@RequestBody CreateBoardRequest createBoardRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        anonymousBoardService.createAnonymousBoard(createBoardRequest, userDetails.getUser());
        return new ResponseEntity<>("게시글 작성완료",HttpStatus.CREATED);
    }

    //게시글 조회
    @GetMapping("/normal")
    public List<BoardResponse> getBoardList() {
        return boardService.getBoardList();
    }

    //익명 게시글 조회
    @GetMapping("/anonymous")
    public List<AnonymousBoardResponse> getAnonymousBoardList() {
        return anonymousBoardService.getAnonymousBoardList();
    }

    //게시글 수정
    @PatchMapping("/normal/{boardId}") //인증필요 , 수정할 때 이미지나 내용중 하나만 변경하고 싶을 때는?
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.updateBoard(boardId,updateBoardRequest,userDetails.getMember());
        return new ResponseEntity<>("게시글 수정완료",HttpStatus.OK);
    }

    //익명 게시글 수정
    @PatchMapping("/anonymous/{boardId}")
    public ResponseEntity<String> updateAnonymousBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        anonymousBoardService.updateAnonymousBoard(boardId,updateBoardRequest,userDetails.getMember());
        return new ResponseEntity<>("게시글 수정완료",HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/normal/{boardId}")  //인증필요
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId,userDetails.getMember());
        return new ResponseEntity<>("게시글 삭제완료",HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/anonymous/{boardId}")  //인증필요
    public ResponseEntity<String> deleteAnonymousBoard(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        anonymousBoardService.deleteAnonymousBoard(boardId,userDetails.getMember());
        return new ResponseEntity<>("게시글 삭제완료",HttpStatus.OK);
    }

}

//문제점 보드를 함께 써서 보드 번호가 애매함