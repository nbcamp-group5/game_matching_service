package com.nbcamp.gamematching.matchingservice.comment.controller;

import com.nbcamp.gamematching.matchingservice.comment.dto.CreateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{boardId}")
    public ResponseEntity<String> createComment(@PathVariable Long boardId, @RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createComment(boardId,createCommentRequest.getContent());
        return new ResponseEntity<>("댓글 작성완료", HttpStatus.CREATED);
    }

    //익명 댓글 작성
    @PostMapping("/anonymous/{boardId}")
    public ResponseEntity<String> createAnonymousComment(@PathVariable Long boardId, @RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createAnonymousComment(boardId,createCommentRequest.getContent());
        return new ResponseEntity<>("댓글 작성완료", HttpStatus.CREATED);
    }

    //댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        commentService.updateComment(commentId,updateCommentRequest.getContent());
        return new ResponseEntity<>("댓글 수정완료",HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제완료",HttpStatus.OK);
    }
}
