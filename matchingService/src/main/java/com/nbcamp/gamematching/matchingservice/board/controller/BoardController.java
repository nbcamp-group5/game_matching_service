package com.nbcamp.gamematching.matchingservice.board.controller;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.service.AnonymousBoardService;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.member.domain.FileStore;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final AnonymousBoardService anonymousBoardService;

    @Value("${file.dir}")
    private String fileDir;

    private final FileStore fileStore;


    //게시글 작성
    @PostMapping(value = "/normal")
    public ResponseEntity<String> createBoard(
            @RequestPart("requestDto") CreateBoardRequest createBoardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart("image") MultipartFile image) throws IOException {
        boardService.createBoard(createBoardRequest, userDetails.getUser(), image);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
//        return "board/create";
    }

    //익명 게시글 작성
    @PostMapping(value = "/anonymous")
    public ResponseEntity<String> createAnonymousBoard(
            @RequestPart CreateBoardRequest createBoardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart MultipartFile image)
            throws IOException {
        anonymousBoardService.createAnonymousBoard(createBoardRequest, userDetails.getUser(),
                image);
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.CREATED);
//        return "templates/anonymous/create";
    }

    //게시글 조회
    @GetMapping("/normal")
    public List<BoardResponse> getBoardList() {
        return boardService.getBoardList();
//        model.addAttribute("boardResponseList",boardResponseList);
//        model.addAttribute("board",board);
//        return "board/main";
    }

    //익명 게시글 조회
    @GetMapping(value = "/anonymous")
    public List<AnonymousBoardResponse> getAnonymousBoardList() {
        return anonymousBoardService.getAnonymousBoardList();
//        model.addAttribute("getAnonymousList",anonymousBoardResponseList);
//        model.addAttribute("board",board);
//        return "anonymous/main";
    }

    //게시글 수정
    @PutMapping(value = "/normal/{boardId}") //수정할 때 이미지나 내용중 하나만 변경하고 싶을 때는?
    public ResponseEntity<String> updateBoard(@PathVariable("boardId") Long boardId,
            @RequestPart UpdateBoardRequest updateBoardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart MultipartFile image)
            throws IOException {
        boardService.updateBoard(boardId, updateBoardRequest, userDetails.getMember(), image);
        return new ResponseEntity<>("게시글 수정완료", HttpStatus.OK);
        //        return "board/update";
    }

    //익명 게시글 수정
    @PatchMapping(value = "/anonymous/{boardId}")
    public ResponseEntity<String> updateAnonymousBoard(@PathVariable Long boardId,
            @RequestPart UpdateBoardRequest updateBoardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart MultipartFile image)
            throws IOException {
        anonymousBoardService.updateAnonymousBoard(boardId, updateBoardRequest,
                userDetails.getMember(), image);
        return new ResponseEntity<>("게시글 수정완료", HttpStatus.OK);
//        return "anonymous/update";
    }

    //게시글 삭제
    @DeleteMapping(value = "/normal/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(boardId, userDetails.getMember());
        return new ResponseEntity<>("게시글 삭제완료", HttpStatus.OK);
//        return "board/delete";
    }

    //게시글 삭제
    @DeleteMapping(value = "/anonymous/{boardId}")
    public ResponseEntity<String> deleteAnonymousBoard(@PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        anonymousBoardService.deleteAnonymousBoard(boardId, userDetails.getMember());
        return new ResponseEntity<>("게시글 삭제완료", HttpStatus.OK);
//        return "anonymous/delete";
    }

//    //게시글 조회
//    @GetMapping(value = "/search/{searchName}")
//    public List<BoardResponse> getBoardList1(@PathVariable String searchName) {
//        return boardService.getBoardList1(searchName);
////        model.addAttribute("boardResponseList",boardResponseList);
////        model.addAttribute("board",board);
////        return "board/main";
//    }

}
