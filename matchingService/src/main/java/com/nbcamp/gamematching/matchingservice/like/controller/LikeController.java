package com.nbcamp.gamematching.matchingservice.like.controller;


import com.nbcamp.gamematching.matchingservice.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    //게시글 좋아요
    @PostMapping("/{boardId}")
    public ResponseEntity<String> likeBoard(@PathVariable Long boardId) {
        likeService.likeBoard(boardId);
        return new ResponseEntity<>("좋아요 완료", HttpStatus.OK);
    }

    //익명 게시글 좋아요
    @PostMapping("/anonymous/{boardId}")
    public ResponseEntity<String> likeAnonymousBoard(@PathVariable Long boardId) {
        likeService.likeAnonymousBoard(boardId);
        return new ResponseEntity<>("좋아요 완료", HttpStatus.OK);
    }


    //게시글 좋아요 취소
    @PostMapping("/cancel/{boardId}") //게시글 좋아요 취소
    public ResponseEntity<String> hateBoard(@PathVariable Long boardId) {
        likeService.hateBoard(boardId);
        return new ResponseEntity<>("좋아요 취소완료", HttpStatus.OK);
    }

    //익명 게시글 좋아요 취소
    @PostMapping("/anonymous/cancel/{boardId}") //게시글 좋아요 취소
    public ResponseEntity<String> hateAnonymousBoard(@PathVariable Long boardId) {
        likeService.hateAnonymousBoard(boardId);
        return new ResponseEntity<>("좋아요 취소완료", HttpStatus.OK);
    }
}
