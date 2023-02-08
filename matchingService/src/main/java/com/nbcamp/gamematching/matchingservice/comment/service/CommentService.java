package com.nbcamp.gamematching.matchingservice.comment.service;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final AnonymousBoardRepository anonymousBoardRepository;
    private final CommentRepository commentRepository;

    //댓글 작성
    @Transactional
    public void createComment(Long boardId, String content) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Comment comment = new Comment(content, board);
        commentRepository.save(comment);
    }

    //익명 댓글 작성
    @Transactional
    public void createAnonymousComment(Long boardId, String content) {
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Comment comment = new Comment(content, board);
        commentRepository.save(comment);
    }

    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
        //맴버 확인
        comment.updateComment(content);
        commentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
       //맴버 확인
        commentRepository.deleteById(commentId);
    }
}
