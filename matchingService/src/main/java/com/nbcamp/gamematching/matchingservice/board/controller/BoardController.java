package com.nbcamp.gamematching.matchingservice.board.controller;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.service.AnonymousBoardService;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.member.service.FileUploadService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final AnonymousBoardService anonymousBoardService;

    private final FileUploadService fileUploadService;


    //게시글 작성
    @PostMapping(value = "/normal")
        public ResponseEntity<String> createBoard(@RequestPart("requestDto") CreateBoardRequest createBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart(value = "image", required = false) MultipartFile image) {
        boardService.createBoard(createBoardRequest, userDetails.getUser(),image);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
    }

    //익명 게시글 작성
    @PostMapping(value = "/anonymous")
    public ResponseEntity<String> createAnonymousBoard(@RequestPart("requestDto") CreateBoardRequest createBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart(value = "image",required = false) MultipartFile image) {
        anonymousBoardService.createAnonymousBoard(createBoardRequest, userDetails.getUser(),image);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
    }

    //게시글 조회
    @GetMapping("/normal")
    public List<BoardResponse> getBoardList() {
        return boardService.getBoardList();
    }

    //익명 게시글 조회
    @GetMapping(value = "/anonymous")
    public  List<AnonymousBoardResponse> getAnonymousBoardList() {
        return anonymousBoardService.getAnonymousBoardList();
    }

    //게시글 수정
    @PutMapping(value = "/normal/{boardId}") //수정할 때 이미지나 내용중 하나만 변경하고 싶을 때는?
    public ResponseEntity<String> updateBoard(@PathVariable("boardId") Long boardId, @RequestPart("requestDto") UpdateBoardRequest updateBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails,@RequestPart(value = "image", required = false) MultipartFile image)  {
        boardService.updateBoard(boardId,updateBoardRequest,userDetails.getUser(),image);
        return new ResponseEntity<>("게시글 수정완료",HttpStatus.OK);
    }

    //익명 게시글 수정
    @PutMapping(value = "/anonymous/{boardId}")
    public ResponseEntity<String> updateAnonymousBoard(@PathVariable("boardId") Long boardId, @RequestPart("requestDto") UpdateBoardRequest updateBoardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails,@RequestPart(value = "image", required = false) MultipartFile image)  {
        anonymousBoardService.updateAnonymousBoard(boardId,updateBoardRequest,userDetails.getUser(),image);
        return new ResponseEntity<>("게시글 수정완료",HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping(value = "/normal/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        boardService.deleteBoard(boardId,userDetails.getUser());
        return new ResponseEntity<>("게시글 삭제완료",HttpStatus.OK);
    }

    //익명 게시글 삭제
    @DeleteMapping(value = "/anonymous/{boardId}")
    public ResponseEntity<String> deleteAnonymousBoard(@PathVariable("boardId") Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        anonymousBoardService.deleteAnonymousBoard(boardId,userDetails.getUser());
        return new ResponseEntity<>("게시글 삭제완료",HttpStatus.OK);
    }


    //게시글 단건 조회
    @GetMapping("/normal/{boardId}")
    public BoardResponse getBoard(@PathVariable Long boardId) {
        return boardService.getBoard(boardId);
    }

    //익명 게시글 단건 조회
    @GetMapping("/anonymous/{boardId}")
    public AnonymousBoardResponse getAnonymousBoard(@PathVariable Long boardId) {
        return anonymousBoardService.getAnonymousBoard(boardId);
    }





//    @GetMapping(value = "/search/{searchName}")
//    public List<BoardResponse> getBoardList1(@PathVariable String searchName) {
//        return boardService.getBoardList1(searchName);
////        model.addAttribute("boardResponseList",boardResponseList);
////        model.addAttribute("board",board);
////        return "board/main";
//    }

}
