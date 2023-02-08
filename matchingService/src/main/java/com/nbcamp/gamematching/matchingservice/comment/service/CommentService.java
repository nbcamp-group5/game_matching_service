package com.nbcamp.gamematching.matchingservice.comment.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.AnonymousCommentRepository;
import com.nbcamp.gamematching.matchingservice.comment.repository.CommentRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final AnonymousBoardRepository anonymousBoardRepository;
    private final CommentRepository commentRepository;
    private final AnonymousCommentRepository anonymousCommentRepository;

    //댓글 작성
    @Transactional
    public void createComment(Long boardId, String content, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Comment comment = new Comment(content, board,member);
        commentRepository.save(comment);
    }

    //익명 댓글 작성
    @Transactional
    public void createAnonymousComment(Long boardId, String content,Member member) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        AnonymousComment comment = new AnonymousComment(content, board,member);
        anonymousCommentRepository.save(comment);
    }

    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, UpdateCommentRequest updateCommentRequest, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
        comment.checkUser(comment,member);
        comment.updateComment(updateCommentRequest,member);
        commentRepository.save(comment);
    }

    //익명 댓글 수정
    @Transactional
    public void updateAnonymousComment(Long commentId,UpdateCommentRequest updateCommentRequest,Member member) {
        AnonymousComment comment = anonymousCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
        comment.checkUser(comment,member);
        comment.updateComment(updateCommentRequest,member);
        anonymousCommentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId,Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
       comment.checkUser(comment,member);
        commentRepository.deleteById(commentId);
    }

    //익명 댓글 삭제
    @Transactional
    public void deleteAnonymousComment(Long commentId,Member member) {
        AnonymousComment comment = anonymousCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(""));
        comment.checkUser(comment,member);
        anonymousCommentRepository.deleteById(commentId);
    }
}
