package com.nbcamp.gamematching.matchingservice.comment.controller;

import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CreateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.service.CommentServiceImpl;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentServiceImpl commentService;

    //댓글 작성
    @PostMapping("/{boardId}")
    public ResponseEntity<String> createComment(@PathVariable Long boardId, @RequestBody CreateCommentRequest createCommentRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(boardId,createCommentRequest.getContent(),userDetails.getUser());
        return new ResponseEntity<>("댓글 작성완료", HttpStatus.CREATED);
    }

    //익명 댓글 작성
    @PostMapping("/anonymous/{boardId}")
    public ResponseEntity<String> createAnonymousComment(@PathVariable Long boardId, @RequestBody CreateCommentRequest createCommentRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createAnonymousComment(boardId,createCommentRequest.getContent(),userDetails.getUser());
        return new ResponseEntity<>("댓글 작성완료", HttpStatus.CREATED);
    }

    //댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentId,updateCommentRequest,userDetails.getUser());
        return new ResponseEntity<>("댓글 수정완료",HttpStatus.OK);
    }

    //익명 댓글 수정
    @PutMapping("/anonymous/{commentId}")
    public ResponseEntity<String> updateAnonymousComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateAnonymousComment(commentId,updateCommentRequest,userDetails.getUser());
        return new ResponseEntity<>("댓글 수정완료",HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId,userDetails.getUser());
        return new ResponseEntity<>("댓글 삭제완료",HttpStatus.OK);
    }

    //익명 댓글 삭제
    @DeleteMapping("/anonymous/{commentId}")
    public ResponseEntity<String> deleteAnonymousComment(@PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteAnonymousComment(commentId,userDetails.getUser());
        return new ResponseEntity<>("댓글 삭제완료",HttpStatus.OK);
    }

    //댓글 단건 조회
    @GetMapping("/one/{commentId}")
    public CommentResponse getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    //익명 댓글 단건 조회
    @GetMapping("/anonymous/one/{commentId}")
    public AnonymousCommentResponse getAnonymousComment(@PathVariable Long commentId) {
        return commentService.getAnonymousComment(commentId);
    }

    //댓글 조회
    @GetMapping("/{boardId}")
    public List<CommentResponse> showComment(@PathVariable Long boardId) {
       return commentService.showComment(boardId);
    }

    //익명 댓글 조회
    @GetMapping("/anonymous/{boardId}")
    public List<AnonymousCommentResponse> showAnonymousComment(@PathVariable Long boardId) {
        return commentService.showAnonymousComment(boardId);
    }
}
