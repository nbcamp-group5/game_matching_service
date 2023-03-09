package com.nbcamp.gamematching.matchingservice.like.controller;


import com.nbcamp.gamematching.matchingservice.like.service.LikeService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{boardId}")
    public ResponseEntity<String> likeBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likeBoard(boardId,userDetails.getUser());
    }

    //익명 게시글 좋아요
    @PostMapping("/anonymous/{boardId}")
    public ResponseEntity<String> likeAnonymousBoard(@PathVariable Long boardId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
       return likeService.likeAnonymousBoard(boardId,userDetails.getUser());
    }
}
